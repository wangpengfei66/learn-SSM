package com.kaishengit.service;


import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.kaishengit.dao.CommissionDao;
import com.kaishengit.dao.CustomerDao;
import com.kaishengit.dao.InterestDao;
import com.kaishengit.dao.InvestDao;
import com.kaishengit.dao.ProjectDao;
import com.kaishengit.dao.SetDao;
import com.kaishengit.entity.Commission;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Interest;
import com.kaishengit.entity.Invest;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Setting;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;

public class InvestService {
	InvestDao inDao = new InvestDao();
	ProjectDao proDao = new ProjectDao();
	InterestDao interestDao = new InterestDao();
	CustomerDao custDao = new CustomerDao();
	CommissionDao commissionDao = new CommissionDao();
	SetDao setDao = new SetDao();
	public void addInvest(int custId, int proId,int empId, double restMoney, double investMoney, double rate,
			int month, String signDate, String endDate) {
	//增加invest 流水
	Invest invest = new Invest();
	invest.setCustId(custId);
	invest.setProId(proId);
	invest.setEmpId(empId);
	invest.setRate(rate);
	invest.setInvestMoney(investMoney);
	invest.setMonth(month);
	invest.setSignDate(signDate);
	invest.setEndDate(endDate);
	
	//使用insert方法获得investId
	int investId = inDao.saveInvest(invest);
	System.out.println(investId);
	
	//更新剩余金额
	//TODO
	//做修改的时候一般是将需要修改的对象首先获取到，然后整体进行update，这样可以避免重复写DAO
	restMoney = restMoney - investMoney;
	proDao.updatRrestMoneyByProId(restMoney,proId);
	
	//	7.生成N笔利息流水
	// 利息的状态  已领取  未到期  等待领取
	Interest interest = new Interest();
	interest.setCustId(custId);
	interest.setInvestId(investId);
	interest.setEmpId(empId);
	
	double interestMoney = rate * investMoney;
	interest.setInterestMoney(interestMoney);
	
	//循环计算派息日和发放日
	for (int i = 1; i <= month; i++) {
		//获得jodatime的格式化的类的对象
		DateTimeFormatter matter = DateTimeFormat.forPattern("yyyy-MM-dd");
		//把String转jodatime对象
		DateTime dt = DateTime.parse(signDate);
		//在该日期对象的基础上增加一个月
		DateTime dt2 = dt.plus(i);
		//通过DateTimeFormatter类的对象把DateTime转成格式化后String
		interest.setInterestSendday(dt2.toString(matter));
		interestDao.save(interest);
	}
	//	8.修改客户的积分
	//	100：1的比例
	//获取cust对象
	Customer cust = custDao.findById(custId);
	cust.setPoint(cust.getPoint() + (int)(investMoney * 0.01));
	custDao.updatePoint(cust);
	
	//	9.员工佣金
	//	按照时长来算
	Commission commission = new Commission();
	commission.setEmpId(empId);
	commission.setInvestId(investId);
	
	Setting setting = setDao.findById(Setting.LAST_ID);
	double commissionRate = setting.getValue1();
	double commissionMoney = commissionRate * investMoney;
	commission.setCommission(commissionMoney);
	commissionDao.save(commission);
	}
	/**
	 * 获取page对象
	 * @param currentPageNo
	 * @param key
	 * @param value
	 * @return
	 */
	public Page<Invest> getPag(int currentPageNo, String key, String value) {
		int totalNo = inDao.countInvest(key,value);
		
		Page<Invest> page = new Page<>(currentPageNo, totalNo);
		List<Invest> lists = inDao.findByParams(key,value,page.getPageNoDb(),page.getPageSize());
		page.setItems(lists);
		return page;
	}
	public void delInvest(int investId) {
		//1.判断此是否有invest对象
		Invest invest = inDao.findById(investId);
		if(invest != null) {
			//2.根据 state判断是否领取利息  查询state = 1，investid = id 的count是否>0,证明已经领取利息
			int count = interestDao.countByInvestIdAndState(investId,Interest.INTEREST_STATE_HAD_SEND);
			if(count > 0) {
				throw new ServiceException("已领取利息，不能删除");
			}else{
				//3.删除对应的利息流水
				interestDao.delByInvestId(investId);
				//4.还原客户的积分
				Customer cust = custDao.findById(invest.getCustId());
				cust.setPoint(cust.getPoint() - (int)(invest.getInvestMoney() * 0.01));
				custDao.updatePoint(cust);
				//5.删除对应的员工佣金流水
				commissionDao.delByInvestId(investId);
				//6 还原项目的剩余金额
				Project pro = proDao.findById(String.valueOf(invest.getProId()));
				pro.setRestMoney(pro.getRestMoney() + invest.getInvestMoney());
				proDao.updatRrestMoneyByProId(pro);
				//7 删除invest流水
				inDao.delByInvestId(investId);
			}
			
		}else{
			throw new ServiceException("参数异常");
		}
	}
	/**
	 * 解约
	 * @param parseInt
	 */
	public void unuseByInvestId(int investId) {
		//1.判断传回的id对象是否存在
		Invest invest = inDao.findById(investId);
		if(invest != null) {
			//2.30天以内不能解约
			DateTime currentDateTime = new DateTime();//当前事件的dateTime对象
			DateTime signDateTime = new DateTime(invest.getSignDate());
			int day = Days.daysBetween(signDateTime, currentDateTime).getDays();
			if(day > 30) {
				//3.把没有到期的利息流水删除
				interestDao.delByInvestIdAndState(investId,Interest.INTEREST_STATE_NOT_DATE);
				//4.修改invest状态
				invest.setState(Invest.INVEST_STATE_UNUSE);
				inDao.update(invest);
			}else{
				throw new ServiceException("签约不到30天，不能解约");
			}
		}else{
			throw new ServiceException("参数异常");
		}
		
	}
	public Invest findByInvestId(int investId) {
		return inDao.findById(investId);
	}
	/**
	 * 计算客户是否还有投资
	 * @param parseInt
	 * @return
	 */
	public int count(int custId) {
		
		return inDao.countCust(custId);
	}
	
	
	
	
	/**
	 * 续约操作
	 * @param parseInt
	 *//*
	public void renewInvest(int investId) {
		//1.通过id找到上一笔投资的对象，判空处理
		Invest invest = inDao.findById(investId);
		if(invest != null) {
			//2.通过对象获得各种投资信息，再次添加到各种数据库中，再加month个月
			
			
			
		}else{
			throw new ServiceException("参数异常");
		}
		
	
	
	}*/
	

}

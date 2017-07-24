package com.kaishengit.crm.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.entity.SaleChanceRecord;
import com.kaishengit.crm.entity.SaleChanceRecordExample;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.mapper.SaleChanceMapper;
import com.kaishengit.crm.mapper.SaleChanceRecordMapper;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.SaleChanceService;
import com.kaishengit.crm.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceServiceImpl implements SaleChanceService {

    @Value("#{'${chance.progress}'.split(',')}")
    private List<String> progressList;
    @Autowired
    private SaleChanceMapper saleChanceMapper;
    @Autowired
    private SaleChanceRecordMapper saleChanceRecordMapper;
    @Autowired
    private CustomerMapper customerMapper;
    /**
     * 获取所有销售进度数据动态加载到页面上
     */
    @Override
    public List<String> getProgressList() {
        return progressList;
    }

    /**
     * 添加新的销售机会，在salechace表中添加，如果content不为空的话，也需要再另外一个表中添加
     * @param saleChance
     */
    @Override
    @Transactional
    public void saveSaleChance(SaleChance saleChance) {
        saleChance.setCreateTime(new Date());
        saleChance.setLastTime(new Date());
        saleChanceMapper.insert(saleChance);
        //如果内容不为空，则添加一条记录
        if(StringUtils.isNotEmpty(saleChance.getContent())) {
            SaleChanceRecord saleChanceRecord = new SaleChanceRecord();
            saleChanceRecord.setContent(saleChance.getContent());
            saleChanceRecord.setSaleId(saleChance.getId());
            saleChanceRecord.setCreateTime(new Date());
            saleChanceRecordMapper.insert(saleChanceRecord);
        }
    }

    /**
     * 查询销售机会列表
     * @param queryParams
     * @return
     */
    @Override
    public PageInfo<SaleChance> findByParams(Map<String, Object> queryParams) {
        Integer pageNum = (Integer) queryParams.get("pageNo");
        //分页
        PageHelper.startPage(pageNum,5);
        List<SaleChance> saleChanceList = saleChanceMapper.findByParams(queryParams);
        return new PageInfo<>(saleChanceList);
    }

    @Override
    public SaleChance findSaleChanceById(Integer id) {
        return saleChanceMapper.findByIdLoadCustomer(id);
    }

    /**
     * 更新销售机会的进度
     * @param id
     * @param progress
     */
    @Override
    @Transactional
    public void updateSaleChanceProgress(Integer id, String progress) {
        //更新saleChance表中的progress字段，
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
        saleChance.setProgress(progress);
        saleChanceMapper.updateByPrimaryKeySelective(saleChance);
        // 添加跟进记录
        SaleChanceRecord record = new SaleChanceRecord();
        record.setContent("将进度修改为："+progress+"");
        record.setSaleId(id);
        record.setCreateTime(new Date());
        addNewRecord(record);
    }

    @Override
    @Transactional
    public void delSaleChanceById(Integer saleId) {
        //删除跟进记录
        SaleChanceRecordExample saleChanceRecordExample = new SaleChanceRecordExample();
        saleChanceRecordExample.createCriteria().andSaleIdEqualTo(saleId);
        saleChanceRecordMapper.deleteByExample(saleChanceRecordExample);
        // TODO 删除关联文档
        // TODO 删除关联任务
        //删除机会表中的数据
        saleChanceMapper.deleteByPrimaryKey(saleId);
    }
    /**
     * 增加跟进记录
     * @param saleChanceRecord
     */
    @Override
    @Transactional
    public void addNewRecord(SaleChanceRecord saleChanceRecord) {
        //添加跟进记录
        saleChanceRecord.setCreateTime(new Date());
        saleChanceRecordMapper.insert(saleChanceRecord);
        //在机会表中更新最后的时间
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(saleChanceRecord.getSaleId());
        saleChance.setLastTime(new Date());
        saleChanceMapper.updateByPrimaryKeySelective(saleChance);
        //客户表中更新时间
        Customer customer = customerMapper.selectByPrimaryKey(saleChance.getCustId());
        customer.setContactTime(new Date());
        customerMapper.updateByPrimaryKeySelective(customer);
    }


}

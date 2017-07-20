package com.kaishengit.crm.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService{


    @Value("#{'${customer.trade}'.split(',')}") //SpringEL
    private List<String> tradeList;
    @Value("#{'${customer.source}'.split(',')}")
    private List<String> sourceList;
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 获取所有行业数据
     * @return
     */
    @Override
    public List<String> getTrade() {
        return tradeList;
    }

    /**
     * 获取所有客户来源数据
     * @return
     */
    @Override
    public List<String> getSource() {
        return sourceList;
    }

    /**
     * 新增客户
     * @param customer
     * @param id
     */
    @Override
    public void saveCust(Customer customer, Integer id) {
        customer.setAccountId(id);
        customer.setCreateTime(new Date());
        customerMapper.insert(customer);
    }

    /**
     * 根据条件查询特定账户下的客户列表
     * @param queryParams 先分页，pageHelper.startPage
     * @return 需要先查询出来list，然后在new PageInfo的时候将list传进去
     */
    @Override
    public PageInfo<Customer> findPageByParams(Map<String, Object> queryParams) {
        Integer pageNum = (Integer) queryParams.get("pageNum");
        PageHelper.startPage(pageNum,10);
        List<Customer> customerList = customerMapper.findByParams(queryParams);
        return new PageInfo<Customer>(customerList);
    }

    @Override
    public Customer findById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editCust(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void delById(Integer id) {
        //TODO 删除跟进记录
        //TODO 删除日程安排
        //TODO 删除相关资料
        //删除客户 物理删除，逻辑删除，是将状态改为不可用即可
        customerMapper.deleteByPrimaryKey(id);
    }


}

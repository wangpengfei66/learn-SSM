package com.kaishengit.dao;

import com.kaishengit.pojo.Customer;

import com.kaishengit.util.orm.Condition;
import com.kaishengit.util.orm.Page;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao extends BaseDao<Customer,Integer>{

    public Page<Customer> findByPageNumAndCondition(Integer currentPageNum, Integer pageSize, Condition...conditions) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.createAlias("account","a");
        return findByPageNumAndCondition(criteria,currentPageNum,pageSize,conditions);
    }



}

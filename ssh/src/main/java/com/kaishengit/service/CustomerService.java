package com.kaishengit.service;

import com.kaishengit.dao.CustomerDao;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.orm.Condition;
import com.kaishengit.util.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public Customer findById(Integer id) {
        return customerDao.findById(id);
    }

    public void saveOrUpdate(Customer customer) {
        customerDao.save(customer);
    }



    public void deleteById(Integer id) {
        customerDao.deleteById(findById(id));
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public List<Customer> findByProperty(String property,Object value) {
        return customerDao.findByProperty(property,value);
    }

    public List<Customer> findByCondition(Condition condition) {
        return customerDao.findByCondition(condition);
    }
    public List<Customer> findByConditions(Condition...conditions) {
        return customerDao.findByConditions(conditions);
    }

    public Page<Customer> findByPageNum(Integer currentPageNum) {
        return customerDao.findByPageNum(currentPageNum,5);
    }

    public Page<Customer> findByPageNumAndCondition(Integer currentPageNum,Condition...conditions) {
        return customerDao.findByPageNumAndCondition(currentPageNum,5,conditions);
    }

    public Page<Customer> findByPageNumAndConditionWithSort(Integer currentPageNum,String propertyName,Condition...conditions) {
        return customerDao.findByPageNumAndConditionWithSort(currentPageNum,5,propertyName,"desc",conditions);
    }
}

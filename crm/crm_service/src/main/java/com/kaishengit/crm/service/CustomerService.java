package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<String> getTrade();
    List<String> getSource();

    void saveCust(Customer customer, Integer id);

    PageInfo<Customer> findPageByParams(Map<String, Object> queryParams);
}

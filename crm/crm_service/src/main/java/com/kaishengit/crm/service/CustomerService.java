package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    List<String> getTrade();
    List<String> getSource();

    void saveCust(Customer customer, Integer id);

    PageInfo<Customer> findPageByParams(Map<String, Object> queryParams);

    Customer findById(Integer id);

    void editCust(Customer customer);

    void delById(Integer id);

    void shareCustomerToPublic(Customer customer,Account account);

    void transferCustomerToOtherAccount(Customer customer, Integer accountId,Account account);

    List<Customer> findCustomerById(Integer i);

    void exportAccountCustomerToExcel(Account account, OutputStream outputStream);

    List<Customer> findPublicCustomer();

    PageInfo<Customer> findPublicPageByParams(Map<String, Object> queryParams);

    List<Customer> findAll();
}

package com.kaishengit.service;

import com.kaishengit.pojo.Customer;
import com.kaishengit.util.orm.Condition;
import com.kaishengit.util.orm.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void findById() throws Exception {
        Customer customer = customerService.findById(1000);
        System.out.println(customer.getCustName() + " -> " + customer.getAddress());
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setCustName("Libern");
        customer.setAddress("克里夫兰");
        customer.setCreateTime(new Date());
        customerService.saveOrUpdate(customer);
    }

    @Test
    public void update() {
        Customer customer = customerService.findById(1000);
        customer.setCustName("王二牛");
        customerService.saveOrUpdate(customer);
    }

    @Test
    public void deleteById() {
        customerService.deleteById(1011);
    }

    @Test
    public void findAll() {
        List<Customer> customers = customerService.findAll();
        for(Customer customer : customers) {
            System.out.println(customer.getCustName());
            System.out.println(customer.getAddress());
        }
    }

    @Test
    public void findByProperty() {
        List<Customer> customers = customerService.findByProperty("level","★★★");
        for(Customer customer : customers) {
            System.out.println(customer.getCustName());
            System.out.println(customer.getAddress());
        }
    }

    @Test
    public void findByCondition() {
        Condition condition = new Condition("level","★★★","eq");
        List<Customer> customerList = customerService.findByCondition(condition);
        for(Customer customer : customerList) {
            System.out.println(customer.getCustName());
            System.out.println(customer.getAddress());
        }
    }

    @Test
    public void findByConditions() {
        Condition condition = new Condition("level","★★★","eq");
        Condition condition1 = new Condition("source","自动上门","eq");
        List<Customer> customerList = customerService.findByConditions(condition,condition1);
        for(Customer customer : customerList) {
            System.out.println(customer.getCustName());
            System.out.println(customer.getAddress());
        }
    }
    @Test
    public void findByPageNum() {
        Page<Customer> page = customerService.findByPageNum(1);
        System.out.println(page.getTotalNum());
        System.out.println(page.getTotalPageSize());
        List<Customer> customerList = page.getItems();
        for(Customer customer : customerList) {
            System.out.println(customer.getCustName() + " --> " + customer.getAddress());
        }
    }
    @Test
    public void findByPageNumAndCondition() {
        Condition condition = new Condition("level","★★★","eq");
        Page<Customer> page = customerService.findByPageNumAndCondition(1,condition);
        System.out.println(page.getTotalNum());
        System.out.println(page.getTotalPageSize());
        List<Customer> customerList = page.getItems();
        for(Customer customer : customerList) {
            System.out.println(customer.getCustName() + " --> " + customer.getAddress());
        }
    }
    @Test
    public void findByPageNumAndConditionWithSort() {
        Condition condition = new Condition("level","★★★","eq");
        Page<Customer> page = customerService.findByPageNumAndConditionWithSort(1,"id",condition);
        System.out.println(page.getTotalNum());
        System.out.println(page.getTotalPageSize());
        List<Customer> customerList = page.getItems();
        for(Customer customer : customerList) {
            System.out.println(customer.getId() + "-->" + customer.getCustName() + " --> " + customer.getAddress());
        }
    }

}
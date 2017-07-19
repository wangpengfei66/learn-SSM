package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface AccountService {
    void saveAccount(Account account, Integer[] depts);

    List<Account> findAllAccounts();

    Long countAll();


    List<Account> findByDeptId(Integer deptId);

    Long countByDeptId(Integer deptId);

    void delById(Integer id);

    Account findByMobileLoadDept(String mobile, String password);

    void update(String oldPassword, String password,Account account) throws ServiceException;

}

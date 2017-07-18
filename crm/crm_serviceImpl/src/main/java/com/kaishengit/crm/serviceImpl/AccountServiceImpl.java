package com.kaishengit.crm.serviceImpl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDeptExample;
import com.kaishengit.crm.entity.AccountDeptKey;
import com.kaishengit.crm.entity.AccountExample;
import com.kaishengit.crm.mapper.AccountDeptMapper;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.mapper.DeptMapper;
import com.kaishengit.crm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountDeptMapper accountDeptMapper;

    @Override
    @Transactional
    public void saveAccount(Account account, Integer[] depts) {
        //保存新账户
        accountMapper.insert(account);
        //保存新关系账户
        for(Integer dept : depts) {
            AccountDeptKey accountDeptKey = new AccountDeptKey();
            accountDeptKey.setDeptId(dept);
            accountDeptKey.setAccountId(account.getId());

            accountDeptMapper.insert(accountDeptKey);
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountMapper.selectByExample(new AccountExample());
    }
}

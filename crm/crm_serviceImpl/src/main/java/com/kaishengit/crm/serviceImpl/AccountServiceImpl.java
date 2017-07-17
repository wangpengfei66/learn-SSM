package com.kaishengit.crm.serviceImpl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountExample;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void saveAccount() {

    }

    @Override
    public List<Account> findAllAccounts() {
        return accountMapper.selectByExample(new AccountExample());
    }
}

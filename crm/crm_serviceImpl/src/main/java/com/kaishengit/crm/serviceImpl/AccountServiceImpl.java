package com.kaishengit.crm.serviceImpl;

import com.github.pagehelper.PageHelper;
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

    @Override
    public Long countAll() {
        return accountMapper.countByExample(new AccountExample());
    }




  /*  @Override
    public List<Account> getPage(String start, String length) {
        //PageHelper.offsetPage(Integer.valueOf(start),Integer.valueOf(length));
        List<Account> accountList = accountMapper.findAllLoadDept();
        return accountList;
    }*/

    /**
     * 如果ID是1，代表总公司，不查询
     * @param id
     * @return
     */
    @Override
    public List<Account> findByDeptId(Integer deptId) {
        if(new Integer(1).equals(deptId)){
            deptId = null;
        }
        return accountMapper.findByDeptId(deptId);
    }

    @Override
    public Long countByDeptId(Integer deptId) {
        if(new Integer(1).equals(deptId)){
            deptId = null;
        }
        return accountMapper.countByDeptId(deptId);
    }

    @Override
    @Transactional
    public void delById(Integer id) {
        //删除关系
        AccountDeptExample accountDeptExample = new AccountDeptExample();
        accountDeptExample.createCriteria().andAccountIdEqualTo(id);
        accountDeptMapper.deleteByExample(accountDeptExample);
        //删除员工
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andIdEqualTo(id);
        accountMapper.deleteByExample(accountExample);
    }
}

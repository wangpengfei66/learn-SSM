package com.kaishengit.crm.serviceImpl;

import com.kaishengit.crm.entity.AccountDeptExample;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.entity.DeptExample;
import com.kaishengit.crm.mapper.AccountDeptMapper;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.mapper.DeptMapper;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountDeptMapper accountDeptMapper;

    @Override
    public List<Dept> findAllDept() {
        return deptMapper.selectByExample(new DeptExample());
    }

    @Override
    public void save(Dept dept) {
        deptMapper.insert(dept);
    }

    @Override
    @Transactional
    public void delById(Integer id) throws ServiceException{
        //删除部门中有的account
        accountMapper.deleteByDeptId(id);
        // 删除关系表
        AccountDeptExample accountDeptExample = new AccountDeptExample();
        accountDeptExample.createCriteria().andDeptIdEqualTo(id);
        accountDeptMapper.deleteByExample(accountDeptExample);
        // 删除部门 如果部门id为1000 则为公司id，不能删除
        if(id == 1) {
            throw new ServiceException("不能删除公司");
        }
        else{
            deptMapper.deleteByPrimaryKey(id);
        }
    }
}

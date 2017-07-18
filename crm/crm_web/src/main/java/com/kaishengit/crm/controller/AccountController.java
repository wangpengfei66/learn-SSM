package com.kaishengit.crm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private DeptService deptService;

    @GetMapping
    public String  accountList(){
        return "manage/accounts";
    }

    @PostMapping("/depts.json")
    @ResponseBody
    public List<ZTreeNode> loadDeptData() {
        List<Dept> deptList = deptService.findAllDept();
        //高级做法
        List<ZTreeNode> nodeList = Lists.newArrayList(Collections2.transform(deptList, new Function<Dept, ZTreeNode>() {

            @Nullable
            @Override
            public ZTreeNode apply(@Nullable Dept dept) {
                ZTreeNode node = new ZTreeNode();
                node.setId(dept.getId());
                node.setName(dept.getDeptName());
                node.setpId(dept.getpId());
                return node;
            }
        }));
        return nodeList;
        /*List<ZTreeNode> zTreeList = new ArrayList<>();
        for(Dept dept : deptList) {
            ZTreeNode node = new ZTreeNode();
            node.setId(dept.getId());
            node.setName(dept.getDeptName());
            node.setpId(dept.getpId());
            zTreeList.add(node);
        }
        return zTreeList;*/
    }
    @PostMapping("/dept/new")
    @ResponseBody
    public AjaxResult saveNewDept(Dept dept) {
        deptService.save(dept);
        return AjaxResult.success();
    }

    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveAccount(Account account,Integer[] deptId) {
        accountService.saveAccount(account,deptId);
        return AjaxResult.success();
    }

}

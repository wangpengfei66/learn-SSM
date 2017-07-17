package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public String   accountList(){
        return "manage/accounts";
    }


}

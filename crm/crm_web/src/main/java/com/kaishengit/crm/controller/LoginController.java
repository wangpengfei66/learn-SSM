package com.kaishengit.crm.controller;


import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    /**
     * 登录表单提交
     * @param mobile
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/")
    @ResponseBody
    public AjaxResult login(String mobile,String password,HttpSession session) {
        try{
            Account account = accountService.findByMobileLoadDept(mobile,password);
            session.setAttribute("currentUser",account);

            return AjaxResult.success();
        }catch (LoginException ex){
            return AjaxResult.error(ex.getMessage());
        }
    }

    /**
     * 安全退出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message","您已安全退出系统");
        return "redirect:/";
    }

}

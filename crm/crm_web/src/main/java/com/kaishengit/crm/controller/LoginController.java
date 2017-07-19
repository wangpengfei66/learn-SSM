package com.kaishengit.crm.controller;


import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.LoginException;
import com.kaishengit.exception.ServiceException;
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
    /**
     * 用户个人设置
     */
    @GetMapping("profile")
    public String personalSet() {
        return "profile";
    }

    /**
     * 修改个人设置
     * @param oldPassword 旧密码
     * @param password  新密码
     * @param session 从Session中获取对象
     * @return
     */
    @PostMapping("/profile")
    @ResponseBody
    public AjaxResult personalSet(String oldPassword,String password,HttpSession session) {
        //从session中获取对象
        Account account = (Account) session.getAttribute("currentUser");
        try {
            accountService.update(oldPassword, password, account);
            return AjaxResult.success();
        }catch (ServiceException e){
            return AjaxResult.error(e.getMessage());
        }
    }

}

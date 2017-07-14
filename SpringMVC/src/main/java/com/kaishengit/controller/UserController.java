package com.kaishengit.controller;

import com.kaishengit.controller.com.kaishengit.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ModelAndView show(@RequestParam(required = false,defaultValue = "1") int page) {
        System.out.println("page"+page);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/show");
        modelAndView.addObject("name","李思琪");
        return modelAndView;
    }

   /* @GetMapping
    public String show(Model model) {
        model.addAttribute("name","java");
        return "user/show";
    }*/

    @GetMapping("/show/{ClassName:\\b(web|java)\\b\\d+}/{name}")
    public String showUser(@PathVariable String ClassName, @PathVariable String name) {
        System.out.println("ClassName" + ClassName);
        return "user/show";
    }

    @GetMapping("/save")
    public String save() {
        return "user/save";
    }

    @PostMapping("/save")
    public String userSave(User user, String zipCode, RedirectAttributes redirectAttributes) {
        System.out.println("userName" + user.getAddress() + "zipCode" + zipCode);
        redirectAttributes.addAttribute("message","操作成功");
        return "redirect:/user/save";
    }


    @GetMapping(value = "/validate/{userName}",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String validateUser(@PathVariable String userName) {
        if(userName.equals("tom")){
            return "该账户已被使用";
        }else {
            return "可以使用";
        }
    }
    @GetMapping("/api/show")
    @ResponseBody
    public User showUser() {
        User user = new User();
        user.setId(1);
        user.setAddress("北京");
        user.setUserName("张三");
        return user;
    }
    @GetMapping("/api/shows")
    @ResponseBody
    public List<User> userShows() {
        User user = new User();
        user.setId(1);
        user.setAddress("北京");
        user.setUserName("张三");
        User user1 = new User();
        user.setId(2);
        user.setUserName("hja");
        user.setAddress("河南");
        return Arrays.asList(user,user1);
    }

}

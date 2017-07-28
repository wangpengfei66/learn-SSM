package com.kaishengit.crm.controller;

import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/charts")
public class ChartsController {
    @Autowired
    private CustomerService service;
    @GetMapping("/static")
    public String showStatic() {
        return "charts/chartsStatic";
    }
    @GetMapping("/customer")
    public String showCustomerLevel() {
        return "charts/chartsCustomer";
    }
    @GetMapping("/customer/bar.json")
    @ResponseBody
    public AjaxResult customerLevel() {
        List<Map<String,Object>> levelCount = service.countCustomerByLevel();
        return AjaxResult.success(levelCount);
    }
}

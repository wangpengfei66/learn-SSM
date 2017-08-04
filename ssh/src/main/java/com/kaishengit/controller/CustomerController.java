package com.kaishengit.controller;

import com.kaishengit.pojo.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.orm.Condition;
import com.kaishengit.util.orm.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping
    public String list(Model model, @RequestParam(name = "p",defaultValue = "1") Integer currentPageNum,
                       HttpServletRequest request) throws UnsupportedEncodingException {
//        List<Customer> customerList = customerService.findAll();
//        model.addAttribute("customerList",customerList);
       /* List<Condition> conditions = new ArrayList<>();
        if(StringUtils.isNotEmpty(custName)) {
            custName = new String(custName.getBytes("ISO8859-1"),"UTF-8");
            conditions.add(new Condition("custName",custName,"like"));
        }
*/      List<Condition> conditions = Condition.buliderConditionList(request);
        Page<Customer> page = customerService.findByPageNumAndCondition(currentPageNum,conditions.toArray(new Condition[]{}));
        model.addAttribute("page",page);
        return "customer/list";
    }


}

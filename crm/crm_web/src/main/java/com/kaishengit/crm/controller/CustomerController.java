package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.util.StringUtils;
import com.kaishengit.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 查询个人员工列表
     * @return后端查询到的分页结果显示到前端页面，包括分页，和条件搜索
     */
    @GetMapping("/my")
    public String myCust(Model model,
                         @RequestParam(required = false,defaultValue = "1",value = "p") Integer pageNum,
                         @RequestParam(required = false,defaultValue = "") String keyword,
                         HttpSession session) {
        Account account = (Account) session.getAttribute("currentUser");
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("pageNum",pageNum);
        keyword = StringUtils.isoToUtf(keyword);
        queryParams.put("keyword",keyword);
        queryParams.put("accountId",account.getId());
        PageInfo<Customer> pageInfo = customerService.findPageByParams(queryParams);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("keyword",keyword);
        return "customer/customerMy";
    }


    @GetMapping("/new")
    public String newCust(Model model) {
        model.addAttribute("tradeList",customerService.getTrade());
        model.addAttribute("sourceList",customerService.getSource());
        return "customer/customerAdd";
    }

    /**
     * 新增客户
     * @param customer
     * @param session
     * @return
     */
    @PostMapping("/new")
    @ResponseBody
    public AjaxResult newCust(Customer customer, HttpSession session) {
        Account account = (Account) session.getAttribute("currentUser");
        customerService.saveCust(customer,account.getId());
        return AjaxResult.success();
    }


}

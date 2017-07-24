package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.util.StringUtils;
import com.kaishengit.dto.AjaxResult;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    /**
     * 查询个人员工列表
     * @return后端查询到的分页结果显示到前端页面，包括分页，和条件搜索
     */
    @GetMapping("/my")
    public String myCust(Model model,
                         @RequestParam(required = false,defaultValue = "1",value = "p") Integer pageNum,
                         @RequestParam(required = false) String keyword,
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

    /**
     * 到新增客户页面
     * @param model
     * @return
     */
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

    /**
     * 显示客户详情，如果找不到，404
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/my/{id:\\d+}")
    public String custInfo(@PathVariable Integer id,HttpSession session,Model model) {
        Account account = (Account) session.getAttribute("currentUser");
        Customer customer = customerService.findById(id);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        model.addAttribute("accountList",accountService.findAllAccounts());
        model.addAttribute("customer",customer);
        return "customer/customerInfo";
    }

    /**
     * 修改客户
     * @param id
     * @param session
     * @param model
     * @return
     */
    @GetMapping("my/edit/{id:\\d+}")
    public String editMyCust(@PathVariable Integer id, HttpSession session, Model model){
        Account account = (Account) session.getAttribute("currentUser");
        Customer customer = customerService.findById(id);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        model.addAttribute("customer",customer);
        model.addAttribute("tradeList",customerService.getTrade());
        model.addAttribute("sourceList",customerService.getSource());
        return "customer/customerEdit";
    }
    /**
     * 将编辑后的结果提交至服务器
     */
    @PostMapping("/my/edit/{id:\\d+}")
    public String custEdit(Customer customer, HttpSession session, RedirectAttributes redirectAttributes) {
        Account account = (Account) session.getAttribute("currentUser");
        if(!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        customerService.editCust(customer);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/customer/my/"+customer.getId();
    }
    /**
     *删除客户
     */
    @GetMapping("/my/del/{id:\\d+}")
    public String custDel(@PathVariable Integer id,HttpSession session,RedirectAttributes redirectAttributes) {
        Account account = (Account) session.getAttribute("currentUser");
        Customer customer = customerService.findById(id);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        customerService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/customer/my";
    }
    /**
     * 将客户放入公海
     */
    @GetMapping("/my/sharePublic/{custId:\\d+}")
    public String sharePublic(@PathVariable Integer custId,HttpSession session,RedirectAttributes redirectAttributes) {
        Account account = (Account) session.getAttribute("currentUser");
        Customer customer = customerService.findById(custId);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        customerService.shareCustomerToPublic(customer,account);
        redirectAttributes.addFlashAttribute("message","成功将客户["+ customer.getCustName() + "]放入公海");
        return "redirect:/customer/my";
    }
    /**
     * 将客户转交他人
     */
    @GetMapping("/my/{custId:\\d+}/transfer/{accountId:\\d+}")
    public String transferCustomerToOtherAccount(@PathVariable Integer custId,
                                                 @PathVariable Integer accountId,
                                                 HttpSession session,
                                                 RedirectAttributes redirectAttributes){
        Account account = (Account) session.getAttribute("currentUser");
        Customer customer = customerService.findById(custId);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        customerService.transferCustomerToOtherAccount(customer,accountId,account);
        redirectAttributes.addFlashAttribute("message","成功将客户["+customer.getCustName()+ "]转移");
        return "redirect:/customer/my";
    }
    /**
     * 导出excel表格
     */
    @GetMapping("/my/export")
    public void exportExcel(HttpSession session, HttpServletResponse response) throws Exception{
        Account account = (Account) session.getAttribute("currentUser");
        //设置文件的MIME头
        response.setContentType("application/vnd.ms-excel");
        //设置下载的文件名
        response.addHeader("Content-Disposition"," attachment; filename=\"customer.xls\"");
        customerService.exportAccountCustomerToExcel(account,response.getOutputStream());
    }
    /**
     * 显示公海客户
     */
    @GetMapping("/public")
    public String showPublicCustomer(Model model,
                                     @RequestParam(required = false,defaultValue = "1",value = "p") Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "") String keyword) {
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("pageNum",pageNum);
        keyword = StringUtils.isoToUtf(keyword);
        queryParams.put("keyword",keyword);
        PageInfo<Customer> pageInfo = customerService.findPublicPageByParams(queryParams);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("keyword",keyword);
        return "customer/customerPublic";
    }

}

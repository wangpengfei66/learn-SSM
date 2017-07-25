package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.entity.SaleChanceRecord;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.SaleChanceContentService;
import com.kaishengit.crm.service.SaleChanceService;
import com.kaishengit.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SaleChanceController {

    @Autowired
    private SaleChanceService saleChanceService;
    @Autowired
    private SaleChanceContentService saleChanceContentService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TaskService taskService;

    /**
     * 销售机会列表
     * @return
     */
    @GetMapping("/chance")
    public String showMySaleChance(Model model,HttpSession session,
                                   @RequestParam(required = false,defaultValue = "1",name = "p") Integer pageNo,
                                   @RequestParam(required = false) String keyword) {
        Account account = (Account) session.getAttribute("currentUser");
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("accountId",account.getId());
        queryParams.put("pageNo",pageNo);
        queryParams.put("keyword",keyword);
        PageInfo<SaleChance> pageInfo = saleChanceService.findByParams(queryParams);
        model.addAttribute("pageInfo",pageInfo);
        return "chance/chanceMy";
    }
    // !此处应当注意，展示的客户必须时自己的客户
    @GetMapping("/chance/new")
    public String newSaleChance(Model model,HttpSession session){
        Account account = (Account) session.getAttribute("currentUser");
        List<Customer> customerList = customerService.findCustomerById(account.getId());
        //将客户列表和跟进程度传递过去
        model.addAttribute("customerList",customerList);
        model.addAttribute("progressList",saleChanceService.getProgressList());
        return "chance/chanceAdd";
    }
    /**
     * 添加新的销售机会
     */
    @PostMapping("/chance/new")
    public String newSaleChance(SaleChance saleChance, RedirectAttributes redirectAttributes){
        saleChanceService.saveSaleChance(saleChance);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/sales/chance";
    }
    /**
     * 从客户页面进行机会的添加
     */
    @PostMapping("/chance/new/{customerId:\\d+}")
    public String newSaleChanceLoadCustomer(@PathVariable Integer customerId,SaleChance saleChance, RedirectAttributes redirectAttributes){
        saleChanceService.saveSaleChance(saleChance);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/customer/my/" + customerId;
    }

    /**
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/chance/new/${custId:\\d+}")
    public String newSaleChanceLoadCustomer(@PathVariable Integer custId,Model model,HttpSession session){
        Account account = (Account) session.getAttribute("currentUser");
        Customer customer = customerService.findOneCustomerById(custId);
        //将客户列表和跟进程度传递过去
        model.addAttribute("currentCustomer",customer);
        model.addAttribute("progressList",saleChanceService.getProgressList());
        return "redirect:/sales/chance";
    }
    /**
     * 展示机会详情 向jsp页面传三个参数，第一个是saleChance，跟进记录列表，进度列表
     */
    @GetMapping("/chance/{id:\\d+}")
    public String chanceInfo(Model model,@PathVariable Integer id,HttpSession session) {
        SaleChance saleChance = saleChanceService.findSaleChanceById(id);
        Account account = (Account) session.getAttribute("currentUser");
        if(saleChance == null) {
            throw new NotFoundException();
        }
        if(!saleChance.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        //获取该机会对应的跟进记录列表
        List<SaleChanceRecord> recordList = saleChanceContentService.findRecordListBySaleChanceId(id);
        model.addAttribute("taskList",taskService.findTaskByAccountIdAndSaleId(account.getId(),id));
        model.addAttribute("saleChance",saleChance);
        model.addAttribute("progressList",saleChanceService.getProgressList());
        model.addAttribute("recordList",recordList);
        return "chance/chanceInfo";
    }
    /**
     * 更改进度展示
     */
    @PostMapping("/chance/progressUpdate/{id:\\d+}")
    public String updateProgress(@PathVariable Integer id,String progress) {
        saleChanceService.updateSaleChanceProgress(id,progress);
        return "redirect:/sales/chance/" + id;
    }
    /**
     * 添加跟进记录
     */
    @PostMapping("/chance/record/new")
    public String addRecord(SaleChanceRecord saleChanceRecord) {
        saleChanceService.addNewRecord(saleChanceRecord);
        return "redirect:/sales/chance/" + saleChanceRecord.getSaleId();
    }
    /**
     * 删除机会
     */
    @GetMapping("/chance/delChance/{saleId:\\d+}")
    public String delSaleChanceById(@PathVariable Integer saleId,HttpSession session,RedirectAttributes redirectAttributes) {
        SaleChance saleChance = saleChanceService.findSaleChanceById(saleId);
        Account account = (Account) session.getAttribute("currentUser");
        if(saleChance == null) {
            throw new NotFoundException();
        }
        if(!saleChance.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        saleChanceService.delSaleChanceById(saleId);
        redirectAttributes.addFlashAttribute("message","删除机会成功");
        return "redirect:/sales/chance";
    }
}

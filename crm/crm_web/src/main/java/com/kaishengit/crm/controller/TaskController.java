package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.service.TaskService;
import com.kaishengit.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{
    @Autowired
    private TaskService taskService;
    @GetMapping("/my")
    public String showTask() {
        return "task/taskMy";
    }

    @GetMapping("/my/new")
    public String addNewTask() {
        return "task/taskAdd";
    }

    @PostMapping("/my/new")
    public String addNewTask(Task task, RedirectAttributes redirectAttributes, HttpSession session) {
        Account account = (Account) session.getAttribute("currentUser");
        taskService.saveNewTask(task,account.getId());
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/task/my";
    }

    /**
     * 关联客户新增
     * @param task
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/my/new/{customerId:\\d+}")
    public String addNewTask(Task task, RedirectAttributes redirectAttributes) {
        taskService.saveNewTask(task);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/customer/my/"+task.getCustomerId();
    }

    /**
     * 关联销售机会新增待办事项
     * @param saleId
     * @param task
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/my/{saleId:\\d+}/new")
    public String addNewTaskLoadSaleChance(@PathVariable Integer saleId, Task task, RedirectAttributes redirectAttributes) {
        taskService.saveNewTask(task);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/sales/chance/"+task.getSaleId();
    }

}

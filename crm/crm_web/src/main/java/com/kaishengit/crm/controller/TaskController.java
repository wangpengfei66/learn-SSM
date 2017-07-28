package com.kaishengit.crm.controller;

import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.service.TaskService;
import com.kaishengit.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{
    @Autowired
    private TaskService taskService;

    /**
     * 待办任务首页
     * @return
     */
    @GetMapping("/my")
    public String showTask(Model model,HttpSession session,
                           @RequestParam(required = false,defaultValue = "") String show) {
        Account account = (Account) session.getAttribute("currentUser");
        boolean showAll = "all".equals(show) ? true : false;
        List<Task> taskList = taskService.findTaskByAccountId(account.getId(),showAll);
        model.addAttribute("taskList",taskList);
        return "task/taskMy";
    }

    @GetMapping("/my/new")
    public String addNewTask() {
        return "task/taskAdd";
    }

    @PostMapping("/my/new")
    public String addNewTask(Task task, RedirectAttributes redirectAttributes, HttpSession session) {
        Account account = (Account) session.getAttribute("currentUser");
        task.setAccountId(account.getId());
        taskService.saveNewTask(task);
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
    /**
     * 修改待办事项的状态 已完成 | 未完成
     */
    @GetMapping("/my/{id:\\d+}/state/{state}")
    public String changeTaskState(@PathVariable Integer id,@PathVariable String state,HttpSession session) {
        Task task = taskService.findById(id);
        Account account = (Account) session.getAttribute("currentUser");
        if(task == null) {
            throw new NotFoundException();
        }
        if(!task.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        //根据state变量的值来决定对象的状态
        if("done".equals(state)){
            task.setDone(true);
        }else {
            task.setDone(false);
        }
        taskService.updateById(task);
        return "redirect:/task/my";
    }
    /**
     * 删除待办事项
     */
    @GetMapping("/my/del/{id:\\d+}")
    public String delTaskById(@PathVariable Integer id,
                              RedirectAttributes redirectAttributes,HttpSession session) {
        Task task = taskService.findById(id);
        Account account = (Account) session.getAttribute("currentUser");
        if(task == null) {
            throw new NotFoundException();
        }
        if(!task.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        taskService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/task/my";
    }
    /**
     * 编辑待办事项
     */
    @PostMapping("/my/edit")
    public String editTaskById(Task task) {
        taskService.updateById(task);
        return "redirect:/task/my";
    }
    /**
     * 编辑task向前端传值
     */
    @GetMapping("/my/edit/{id:\\d+}")
    public String editTaskLoadTask(@PathVariable Integer id,HttpSession session,Model model) {
        Task task = taskService.findById(id);
        Account account = (Account) session.getAttribute("currentUser");
        if(task == null) {
            throw new NotFoundException();
        }
        if(!task.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        model.addAttribute("currentTask",task);
        return "task/taskEdit";
    }

}

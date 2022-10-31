package com.task.management.system.app.controller;

import com.task.management.system.app.Service.AdminService;
import com.task.management.system.app.model.Task;
import com.task.management.system.app.model.TimeEstimation;
import com.task.management.system.app.model.User;
import com.task.management.system.app.model.Worklogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    private int taskId = 0;

    @GetMapping("/add_task")
    public String openTaskPage() {

        return "add_task";
    }

    @PostMapping(value = "/save_task")
    public String saveTask(@ModelAttribute("task") Task task, Model model) {

        adminService.saveTask(task);
        model.addAttribute("massg", "Added Task Successfully  ");
        return "success";
    }

    @GetMapping(value = "/view_user_detail/{id}")
    public String viewUserAndUpdate(@PathVariable("id") int id, @ModelAttribute("user") User user, Model m) {

        User u = adminService.viewUserAndUpdate(id);
        m.addAttribute("user", u);
        return "view_user_detaila";
    }
    @PostMapping(value = "/save_user")
    public String saveUser(@ModelAttribute("user") User user, Model model) {

        adminService.saveUser(user);
        model.addAttribute("massg", "Update Successfully Please Go Back ");
        return "success";
    }
    @GetMapping("/view_list_task")
    public String ViewListOfTask(Model model) {

        List<Task> taskList = adminService.findAllTask();
        model.addAttribute("tasklist", taskList);
        return "list_of_task";
    }

    @GetMapping("/assing_task_to_user/{id}")
    public String assingTaskToUser(@PathVariable("id") int id, Model model) {

        taskId = id;
        List<User> userList = adminService.findAllUser();
        model.addAttribute("userList", userList);
        model.addAttribute("user", new User());
        return "assing_task_to_user";
    }

    @PostMapping("/assing_task_to_user")
    public String assingTaskToUser(@ModelAttribute("user") User user, Model model) {

        adminService.saveTaskToTheUser(user, taskId);
        model.addAttribute("massg", "Successfully Assing Task To User");
        return "success";
    }
    @GetMapping("/view_worklog/{id}")
    public String viewWorklog(@PathVariable("id") int id, Model m) {
        taskId=id;
        List<Worklogs> listWorklog = adminService.viewWorklogById(id);
        m.addAttribute("listWorklog", listWorklog);
        return "view_worklog";
    }

    @GetMapping("/total_time_spend")
    public String totalTimeSpend(Model model){

        TimeEstimation timeEstimation =adminService.getTotalTimeSpend(taskId);
        model.addAttribute("timeEstimation", timeEstimation);

        return "total_time_spend";
    }
}

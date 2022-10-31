package com.task.management.system.app.controller;

import com.task.management.system.app.Service.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private int tid = 0;

    @GetMapping(value = "/view_user_detail/{id}")
    public String viewUserAndUpdate(@PathVariable("id") int id, @ModelAttribute("user") User user, Model m) {

        User u = employeeService.viewUserAndUpdate(id);
        m.addAttribute("user", u);
        return "view_user_detail";
    }

    @PostMapping(value = "/save_user")
    public String saveUser(@ModelAttribute("user") User user, Model model) {

        employeeService.saveUser(user);
        model.addAttribute("massg", "Update Successfully Please Go Back  ");
        return "success";
    }

    @GetMapping(value = "/list_of_task/{id}")
    public String listOfTask(@PathVariable("id") int id, Model m) {

        List<Task> taskList = employeeService.listOfTaskByUserId(id);
        m.addAttribute("tasklist", taskList);
        return "list_of_task";
    }

    @GetMapping(value = "/add_worklog/{id}")
    public String openWorkLogePage(@PathVariable("id") int id) {

        tid = id;
        return "add_worklog";
    }

    @PostMapping(value = "/save_worklog")
    public String saveWorkLoge(@ModelAttribute("worklog") Worklogs worklogs, Model model) {

        employeeService.saveWorkLogs(worklogs, tid);
        model.addAttribute("massg", "Added Worklog Successfully  ");
        return "success";
    }

    @GetMapping(value = "/update_worklog/{id}")
    public String updateWorkLoge(@PathVariable("id") int id, Model model) {

        Worklogs worklogs = employeeService.findWorkLogById(id);
        model.addAttribute("worklog", worklogs);
        return "update_worklog";
    }

    @PostMapping(value = "update_worklog")
    public String updateWorkLoge(@ModelAttribute("worklog") Worklogs worklogs, Model model) {

        employeeService.updateWorkLog(worklogs);
        model.addAttribute("massg", "Update Worklogs Successfully ! ");
        return "success";
    }

    @GetMapping("/view_worklog/{id}")
    public String viewWorklog(@PathVariable("id") int id, Model model) {
        tid = id;
        List<Worklogs> listWorklog = employeeService.viewWorklogById(id);
        model.addAttribute("listWorklog", listWorklog);
        return "view_worklog";
    }
    @GetMapping("/total_time_spend")
    public String totalTimeSpend(Model model){

        TimeEstimation timeEstimation =employeeService.getTotalTimeSpend(tid);
        model.addAttribute("timeEstimation", timeEstimation);

        return "total_time_spend";
    }


}

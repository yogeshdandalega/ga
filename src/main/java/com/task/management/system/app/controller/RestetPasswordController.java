package com.task.management.system.app.controller;


import com.task.management.system.app.Service.EmployeeService;
import com.task.management.system.app.Service.ResetPasswordService;
import com.task.management.system.app.model.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RestetPasswordController {


    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResetPasswordService resetPasswordService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String viewHomePageWithUserList(Model model) {

        List<User> userList = employeeService.findAllUser();
        model.addAttribute("userList", userList);
        return "index";
    }
    @GetMapping("/register_user")
    public String openUserRegisterPage(Model model) {

        return "register_user";
    }
    @PostMapping(value = "/save_user")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        System.out.println("JAva"+":"+user.getName());
        resetPasswordService.saveUser(user);

        return "redirect:/login";
    }


    @GetMapping("/pass/reset")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping(value = "/pass/forgot_password")
    public String forgetPassword(HttpServletRequest request, @RequestParam(required = false, value = "username") String username, Model model) {

        String token = RandomString.make(30);
        System.out.println("username :" + username + ":" + token);
        ;
        resetPasswordService.updateResetPasswordToken(username, token);
        model.addAttribute("token", token);
        return "reset_password_form";
    }

    @GetMapping("/pass/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = resetPasswordService.getByForgetPasswordToken(token);
        model.addAttribute("token", token);
        System.out.println("Token" + user.getForgetPasswordToken());

        return "reset_password_form";
    }

    @PostMapping("/pass/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        User user = resetPasswordService.getByForgetPasswordToken(token);

        resetPasswordService.updatePassword(user, password);

        return "login";
    }
}

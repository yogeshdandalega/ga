package com.task.management.system.app.ServiceImpl;


import com.task.management.system.app.Repository.UserRepository;
import com.task.management.system.app.Repository.WorklogRepository;
import com.task.management.system.app.Service.ResetPasswordService;
import com.task.management.system.app.model.Role;
import com.task.management.system.app.model.User;
import com.task.management.system.app.model.Worklogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorklogRepository worklogRepository;

    @Override
    public void updateResetPasswordToken(String username, String token) {

        User user = userRepository.getUserByUsername(username);
        user.setForgetPasswordToken(token);
        userRepository.save(user);
    }

    @Override
    public User getByForgetPasswordToken(String token) {

        User user = userRepository.findByForgetPasswordToken(token);
        return user;
    }

    @Override
    public void updatePassword(User user, String newPassword) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        Role r=new Role(2,"EMPLOYEE");
        user.getRoles().add(r);
        user.setForgetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user) {

        user.setEnabled(true);
        user.setForgetPasswordToken(null);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String pass = b.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }


}

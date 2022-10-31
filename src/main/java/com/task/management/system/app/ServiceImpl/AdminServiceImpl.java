package com.task.management.system.app.ServiceImpl;

import com.task.management.system.app.Repository.TaskRepository;
import com.task.management.system.app.Repository.UserRepository;

import com.task.management.system.app.Service.AdminService;
import com.task.management.system.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.task.management.system.app.model.TimeConverter.timeConvertIntoDay;
import static com.task.management.system.app.model.TimeConverter.timeConvertIntoDayHourseMinutes;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;


    @Override
    public void saveTask(Task task) {

        taskRepository.save(task);
    }

    @Override
    public User viewUserAndUpdate(int id) {

        User user = userRepository.findById(id).get();

        return user;
    }

    @Override
    public void saveUser(User user) {

        user.setEnabled(true);
        Role role = new Role(2, "EMPLOYEE");
        user.getRoles().add(role);
        user.setForgetPasswordToken(null);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String pass = b.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }

    @Override
    public List<Task> findAllTask() {
        List<Task> taskList = taskRepository.findAll();
        List<Task> list = new ArrayList<>();
        String time = "";
        for (Task task : taskList) {
            time = timeConvertIntoDay(task.getEstimate());
            Task tasks = new Task();
            tasks.setId(task.getId());
            tasks.setName(task.getName());
            tasks.setEstimate(time);
            tasks.setPriority(task.getPriority());
            tasks.setSummary(task.getSummary());
            list.add(tasks);
        }
        return list;

    }

    @Override
    public List<User> findAllUser() {

        List<User> userList = userRepository.findAll();
        return userList;

    }


    @Override
    public void saveTaskToTheUser(User user, int taskid) {

        String name = user.getName();
        Task task = taskRepository.findById(taskid).get();
        User user1 = userRepository.findByName(name);
        task.setUser(user);
        taskRepository.save(task);

    }

    @Override
    public List<Worklogs> viewWorklogById(int id) {

        Task task = taskRepository.findById(id).get();
        List<Worklogs> listWorklog = task.getWorklog();
        List<Worklogs> list = new ArrayList<>();
        String time = "";
        for (Worklogs worklogs : listWorklog) {
            time = timeConvertIntoDayHourseMinutes(worklogs.getTimeSpent());
            Worklogs worklog = new Worklogs();
            worklog.setId(worklogs.getId());
            worklog.setDate(worklogs.getDate());
            worklog.setTimeSpent(time);
            worklog.setWorkDescription(worklogs.getWorkDescription());
            list.add(worklog);
        }
        return list;

    }

    @Override
    public TimeEstimation getTotalTimeSpend(int taskId) {

        Task task = taskRepository.findById(taskId).get();
        String estimate = task.getEstimate();
        List<String> stringStream = task.getWorklog().stream().map(Worklogs::getTimeSpent).collect(Collectors.toList());
        double totalTimeSpentIntoHourse = 0;
        for (String s : stringStream) {
            double v = Double.parseDouble(s);
            totalTimeSpentIntoHourse += v;
        }
        double totalDayIntoHourse = Double.parseDouble(estimate) * 8;
        double remainingTime = totalDayIntoHourse - totalTimeSpentIntoHourse;
        String estimates = timeConvertIntoDay(estimate);
        String timeSpent = timeConvertIntoDayHourseMinutes(Double.toString(totalTimeSpentIntoHourse));
        String timeRemaining = timeConvertIntoDayHourseMinutes(Double.toString(remainingTime));

        TimeEstimation timeEstimation = new TimeEstimation();
        timeEstimation.setEstimate(estimates);
        timeEstimation.setTimeSpent(timeSpent);
        timeEstimation.setTimeRemaining(timeRemaining);

        return timeEstimation;
    }


}

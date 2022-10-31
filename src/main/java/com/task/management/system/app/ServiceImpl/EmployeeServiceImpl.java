package com.task.management.system.app.ServiceImpl;

import com.task.management.system.app.Repository.TaskRepository;
import com.task.management.system.app.Repository.UserRepository;
import com.task.management.system.app.Repository.WorklogRepository;
import com.task.management.system.app.Service.EmployeeService;

import com.task.management.system.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.task.management.system.app.model.TimeConverter.timeConvertIntoDay;
import static com.task.management.system.app.model.TimeConverter.timeConvertIntoDayHourseMinutes;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private WorklogRepository worklogRepository;

    @Override
    public void saveUser(User user) {

        user.setEnabled(true);
        user.setForgetPasswordToken(null);
        Role role=new Role(2,"EMPLOYEE");
        user.getRoles().add(role);
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String pass = b.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }

    @Override
    public User viewUserAndUpdate(int id) {

        User user = userRepository.findById(id).get();

        return user;
    }

    @Override
    public List<Task> listOfTaskByUserId(int id) {

        User user = userRepository.findById(id).get();
        List<Task> listTask = user.getTask();
        List<Task>list=new ArrayList<>();
        String time="";
        for (Task task:listTask) {
            time = timeConvertIntoDay(task.getEstimate());
            Task task1=new Task();
            task1.setId(task.getId());
            task1.setName(task.getName());
            task1.setEstimate(time);
            task1.setPriority(task.getPriority());
            task1.setSummary(task.getSummary());
            list.add(task1);
        }
        return list;
    }

    @Override
    public List<User> findAllUser() {

        return userRepository.findAll();
    }

    @Override
    public void saveWorkLogs(Worklogs worklogs, int tid) {

        Task task = taskRepository.findById(tid).get();
        String estimate = task.getEstimate();
        worklogs.setTask((Task) task);
        worklogRepository.save(worklogs);
    }


    @Override
    public List<Worklogs> viewWorklogById(int id) {

        Task task = taskRepository.findById(id).get();

        List<Worklogs> listWorklog = task.getWorklog();
        List<Worklogs>list=new ArrayList<>();
        String time="";
        for (Worklogs s:listWorklog) {
             time = timeConvertIntoDayHourseMinutes(s.getTimeSpent());
            Worklogs w=new Worklogs();
            w.setId(s.getId());
            w.setDate(s.getDate());
            w.setTimeSpent(time);
            w.setWorkDescription(s.getWorkDescription());
            list.add(w);
        }
        return list;
    }

    @Override
    public Worklogs findWorkLogById(int id) {

        Worklogs worklogs = worklogRepository.findById(id).get();
        return worklogs;
    }

    @Override
    public void updateWorkLog(Worklogs worklog) {

        int id = worklog.getTask().getId();
        Task task = taskRepository.findById(id).get();
        worklog.setTask(task);
        worklogRepository.save(worklog);


    }

    @Override
    public TimeEstimation getTotalTimeSpend(int tid) {

        Task task = taskRepository.findById(tid).get();
        String estimate= task.getEstimate();
        List<String> stringStream = task.getWorklog().stream().map(Worklogs::getTimeSpent).collect(Collectors.toList());
        double totalTimeSpentIntoHourse=0;
        for (String s:stringStream) {
                double v = Double.parseDouble(s);
            totalTimeSpentIntoHourse+=v;
            }
        double totalDayIntoHourse = Double.parseDouble(estimate)*8;
        double remainingTime=totalDayIntoHourse-totalTimeSpentIntoHourse;
        String estimates =timeConvertIntoDay(estimate);
        String timeSpent =timeConvertIntoDayHourseMinutes(Double.toString(totalTimeSpentIntoHourse));
        String timeRemaining =timeConvertIntoDayHourseMinutes(Double.toString(remainingTime));

        TimeEstimation timeEstimation=new TimeEstimation();
        timeEstimation.setEstimate(estimates);
        timeEstimation.setTimeSpent(timeSpent);
        timeEstimation.setTimeRemaining(timeRemaining);

            return timeEstimation;
    }



}

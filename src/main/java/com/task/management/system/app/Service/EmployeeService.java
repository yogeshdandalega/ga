package com.task.management.system.app.Service;

import com.task.management.system.app.model.Task;
import com.task.management.system.app.model.TimeEstimation;
import com.task.management.system.app.model.User;
import com.task.management.system.app.model.Worklogs;

import java.util.List;

public interface EmployeeService {
    public void saveUser(User user);

    public User viewUserAndUpdate(int id);

    public List<Task> listOfTaskByUserId(int id);

    public List<User> findAllUser();

    public void saveWorkLogs(Worklogs worklogs, int tid);

    public List<Worklogs> viewWorklogById(int id);

    public Worklogs findWorkLogById(int id);

    public void updateWorkLog(Worklogs worklog);


   public TimeEstimation getTotalTimeSpend(int tid);
}

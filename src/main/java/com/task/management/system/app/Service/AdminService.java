package com.task.management.system.app.Service;


import com.task.management.system.app.model.Task;
import com.task.management.system.app.model.TimeEstimation;
import com.task.management.system.app.model.User;
import com.task.management.system.app.model.Worklogs;

import java.util.List;

public interface AdminService {
   public void saveTask(Task task);

   public List<Task> findAllTask();

   public List<User> findAllUser();

   public void saveTaskToTheUser(User user,int taskId);

   public List<Worklogs> viewWorklogById(int id);

   public User viewUserAndUpdate(int id);

   public void saveUser(User user);

   public TimeEstimation getTotalTimeSpend(int taskId);
}

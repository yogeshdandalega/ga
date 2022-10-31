package com.task.management.system.app.Service;


import com.task.management.system.app.model.User;

public interface ResetPasswordService {
  public  void updateResetPasswordToken(String username, String token);

  public User getByForgetPasswordToken(String token);

  public   void updatePassword(User user, String password);


   public void saveUser(User user);


}

package com.taskmanagement.userTask.service;

import com.taskmanagement.userTask.model.User;

import java.util.List;

public interface UserService {
    public User getUserProfile(String jwt);
    public List<User> getAllUser();
}

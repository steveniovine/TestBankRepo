package com.ey.userservice.services;

import com.ey.userservice.models.User;

import java.util.List;


public interface UserService {

    User getUserById(int id);
    List<User> getAllUsers();
    User createUser(User user);

    User findUser(String username, String password);
    User login(User user);




}

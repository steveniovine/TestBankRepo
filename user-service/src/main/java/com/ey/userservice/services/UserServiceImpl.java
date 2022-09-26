package com.ey.userservice.services;

import com.ey.userservice.models.User;
import com.ey.userservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User findUser(String username, String password) {
        List<User> users = (List<User>) userRepo.findAll();

        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getUser_password())) {
                return users.get(i);
            }
        }
        return new User();
    }

    @Override
    public User login(User user) {
        return user;
    }
}

package com.ey.accountservice.services;

import com.ey.accountservice.models.User;
import com.ey.accountservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }
}

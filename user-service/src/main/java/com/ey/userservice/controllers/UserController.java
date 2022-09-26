package com.ey.userservice.controllers;

import com.ey.userservice.models.User;
import com.ey.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService us;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(us.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return ResponseEntity.ok(us.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String name,
                                           @RequestParam String username,
                                           @RequestParam String password){

        User newUser = new User();
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setUser_password(password);


        return ResponseEntity.ok(us.createUser(newUser));

    }

    @GetMapping("login")
    public ResponseEntity<User> login(@RequestParam String username,
                                      @RequestParam String password) {

        User user_found = us.findUser(username, password);
        if (user_found.getUsername().equals(username) && user_found.getUser_password().equals(password)) {
            return ResponseEntity.ok(us.login(user_found));
        }
        return ResponseEntity.notFound().build();
    }

}

package com.gianca1994.springbootjwt.controller;

import com.gianca1994.springbootjwt.model.User;
import com.gianca1994.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<User> getUsers() {
        return userService.getUsers();
    }
}

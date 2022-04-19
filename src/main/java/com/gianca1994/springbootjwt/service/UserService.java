package com.gianca1994.springbootjwt.service;

import java.util.ArrayList;

import com.gianca1994.springbootjwt.model.User;
import com.gianca1994.springbootjwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }
}

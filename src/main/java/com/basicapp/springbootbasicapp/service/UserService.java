package com.basicapp.springbootbasicapp.service;

import org.springframework.stereotype.Service;

import com.basicapp.springbootbasicapp.entity.User;

@Service
public interface UserService {
    
    public Iterable<User> getAllUsers();

    public User createUser(User user) throws Exception;

}



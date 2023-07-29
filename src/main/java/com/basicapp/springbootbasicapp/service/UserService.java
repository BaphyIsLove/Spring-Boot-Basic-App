package com.basicapp.springbootbasicapp.service;

import org.springframework.stereotype.Service;

import com.basicapp.springbootbasicapp.entity.User;
import com.basicapp.springbootbasicapp.dto.ChangePassword;

@Service
public interface UserService {
    
    public Iterable<User> getAllUsers();

    public User createUser(User user) throws Exception;

    public User getUserById(Long id) throws Exception; 

    public User updateUser(User user) throws Exception;

    public void deleteUser(Long id) throws Exception;

    public User ChangePassword(ChangePassword form) throws Exception;

}



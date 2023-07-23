package com.basicapp.springbootbasicapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basicapp.springbootbasicapp.entity.User;
import com.basicapp.springbootbasicapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    private boolean checkUsernameAndEmailAvaible(User user) throws Exception{
        Optional<User> userFound = repository.findByUsername(user.getUsername());
        Optional<User> emailFound = repository.findByEmail(user.getEmail());
        if(userFound.isPresent()){
            throw new Exception("Usuario no disponible");
        } else if(emailFound.isPresent()){
            throw new Exception("email ya registrado");
        } else{
            return true;
        }
    }
    
    private boolean checkPasswordValid(User user) throws Exception{
        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new Exception("Las contraseñas no coinciden");
        } else{
            return true;
        }
    }

    @Override
    public User createUser(User user) throws Exception {
        if(checkUsernameAndEmailAvaible(user)&&checkPasswordValid(user)){
            user = repository.save(user);
        }
        return user;
    }

}

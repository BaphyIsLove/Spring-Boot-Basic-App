package com.basicapp.springbootbasicapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.basicapp.springbootbasicapp.entity.User;
import com.basicapp.springbootbasicapp.repository.RoleRepository;
import com.basicapp.springbootbasicapp.repository.UserRepository;
import com.basicapp.springbootbasicapp.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserService userService;

    // pagina de inicio (login)
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // pagina de usuario
    @GetMapping("/userForm")
    public String userForm(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("listTab", "active");
        return "user-form/user-view";
    }

}

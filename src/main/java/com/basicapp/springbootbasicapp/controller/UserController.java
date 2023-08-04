package com.basicapp.springbootbasicapp.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.basicapp.springbootbasicapp.Exception.UsernameOrIdNotExistException;
import com.basicapp.springbootbasicapp.dto.ChangePassword;
import com.basicapp.springbootbasicapp.entity.User;
import com.basicapp.springbootbasicapp.repository.RoleRepository;
import com.basicapp.springbootbasicapp.repository.UserRepository;
import com.basicapp.springbootbasicapp.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserService userService;

    // pagina de inicio (login)
    @GetMapping({"/","/login"})
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
    
    @PostMapping("/userForm")
    public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) throws Exception, Exception{
        if(result.hasErrors()){
            model.addAttribute("userForm", user);
            model.addAttribute("formTab", "active");
        } else {
            try {
                userService.createUser(user);
                model.addAttribute("userForm", new User());
                model.addAttribute("listTab", "active");
            } catch (PermissionDeniedDataAccessException e) {
                model.addAttribute("permisionErrorMessage", e.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab", "active");
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles", roleRepository.findAll());
                return "error/403";
            }
        }
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        
        return "user-form/user-view";
        
    }
    
    @GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name ="id")Long id) throws Exception{
        try {
            User userToEdit = userService.getUserById(id);
            model.addAttribute("userForm", userToEdit);
                
            model.addAttribute("userList", userService.getAllUsers());
            model.addAttribute("roles",roleRepository.findAll());
            model.addAttribute("formTab","active");
            model.addAttribute("editMode","true");
            model.addAttribute("passwordForm", new ChangePassword(id));
            return "user-form/user-view";
            
        } catch (UsernameOrIdNotExistException e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            return "error/404";
        }

	}
    
    @PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) throws Exception {
		try {
			if(result.hasErrors()) {
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
			}else {
				userService.updateUser(user);
			}
		} catch (PermissionDeniedDataAccessException e) {
            model.addAttribute("permisionErrorMessage", e.getMessage());
			model.addAttribute("editMode",true);
			model.addAttribute("userForm", user);
			model.addAttribute("passwordForm",new ChangePassword(user.getId()));
			model.addAttribute("formTab","active");
            return "error/403";
		} 
		return "redirect:/userForm";
	}

    @PostMapping("/editUser/changePassword")
    public ResponseEntity<?> postEditChangePassword(@Valid @RequestBody ChangePassword form, Errors errors){
        try {
            if (errors.hasErrors()) {
	            String result = errors.getAllErrors()
	                        .stream().map(x -> x.getDefaultMessage())
	                        .collect(Collectors.joining("<br/>"));
                throw new Exception(result);
            }

            userService.ChangePassword(form);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("success");
    }
    
    @GetMapping("/userForm/cancel")
    public String cancelUserForm(ModelMap model){
        return "redirect:/userForm";
    }

    @GetMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(Model model, @PathVariable(name ="id")Long id) throws Exception {
		try {
            userService.deleteUser(id);
        } catch (PermissionDeniedDataAccessException e) {
            model.addAttribute("permisionErrorMessage", e.getMessage());
            return "error/403";
        }
		return userForm(model);
	}

}

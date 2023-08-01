package com.basicapp.springbootbasicapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.basicapp.springbootbasicapp.entity.User;
import com.basicapp.springbootbasicapp.repository.UserRepository;
import com.basicapp.springbootbasicapp.dto.ChangePassword;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

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
            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            user = repository.save(user);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("No se encontro ningun usuario con ese id"));    
    }

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
	}

	/**
	 * Map everythin but the password.
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from,User to) {
        // to.setId(from.getId());
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}

    @Override
    public void deleteUser(Long id) throws Exception {
        User userDelete = repository.findById(id).orElseThrow(() -> new Exception("usuario no encontrado")); 
        repository.delete(userDelete);
    }

    @Override
    public User ChangePassword(ChangePassword form) throws Exception {
        User storedUser = repository.findById(form.getId()).orElseThrow(() -> new Exception("usuario no encontrado"));
            if(!isLoggerUserAdmin() && !form.getCurrentPassword().equals(storedUser.getPassword())){
                throw new Exception("contraseña actual incorrecta");
            }
            if(form.getCurrentPassword().equals(form.getNewPassword())){
                throw new Exception("La nueva contraseña no debe ser igual a la actual");
            }
            if(!form.getNewPassword().equals(form.getConfirmPassword())){
                throw new Exception("Las contraseñas no coinciden");
            }

            String encodePassword = passwordEncoder.encode(form.getNewPassword());
            storedUser.setPassword(encodePassword);
            return repository.save(storedUser);
    }

    private boolean isLoggerUserAdmin(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails loggedUser = null;
        if(principal instanceof UserDetails){
            loggedUser = (UserDetails) principal;
            loggedUser.getAuthorities().stream()
                .filter(x -> "ROLE_ADMIN".equals(x.getAuthority()))
                .findFirst().orElse(null);
        }
        return loggedUser != null ?true :false;
    }
    
}

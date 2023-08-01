package com.basicapp.springbootbasicapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basicapp.springbootbasicapp.entity.Role;
import com.basicapp.springbootbasicapp.entity.User;
import com.basicapp.springbootbasicapp.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado"));

        Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
        for(Role roles: appUser.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getDescription());
            grantList.add(grantedAuthority);
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), grantList);
        return userDetails;
    }
    
}

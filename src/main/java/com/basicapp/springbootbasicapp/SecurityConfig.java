package com.basicapp.springbootbasicapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.basicapp.springbootbasicapp.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    String[] resources = new String[]{
        "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
        .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers(resources).permitAll()
                .requestMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                )
            .formLogin(login -> login
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/userForm")
                .failureForwardUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                )
            .logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/login?logout"))
            .build();
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

     public void configuraGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
     }
    
}

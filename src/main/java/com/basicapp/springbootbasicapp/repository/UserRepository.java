package com.basicapp.springbootbasicapp.repository;

import java.util.Optional;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.basicapp.springbootbasicapp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
    public Optional<User> findById(Long id);

}

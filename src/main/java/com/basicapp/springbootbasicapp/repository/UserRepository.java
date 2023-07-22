package com.basicapp.springbootbasicapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.basicapp.springbootbasicapp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}

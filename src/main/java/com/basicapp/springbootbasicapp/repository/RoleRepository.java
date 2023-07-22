package com.basicapp.springbootbasicapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.basicapp.springbootbasicapp.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    
}

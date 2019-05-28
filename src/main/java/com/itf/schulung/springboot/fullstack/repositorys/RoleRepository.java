package com.itf.schulung.springboot.fullstack.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.itf.schulung.springboot.fullstack.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}

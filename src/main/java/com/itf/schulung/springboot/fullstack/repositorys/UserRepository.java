package com.itf.schulung.springboot.fullstack.repositorys;

import org.springframework.data.repository.CrudRepository;

import com.itf.schulung.springboot.fullstack.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

}

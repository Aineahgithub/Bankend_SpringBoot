package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.itf.schulung.springboot.fullstack.model.Login;
import com.itf.schulung.springboot.fullstack.model.User;

public interface LoginRepository extends CrudRepository<Login, Integer> {

	Login findByUser(User user);

	List<Login> findByUserAndTimeBetween(User user, Date from, Date to);

}

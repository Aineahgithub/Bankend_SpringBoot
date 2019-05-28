package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import com.itf.schulung.springboot.fullstack.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	List<Account> findAll();

	List<Account> findAllByOrderByIdDesc();

}

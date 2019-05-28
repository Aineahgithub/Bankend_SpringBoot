package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.itf.schulung.springboot.fullstack.model.AccountHelper;


public interface AccountHelperRepository extends CrudRepository<AccountHelper, Integer> {
	List<AccountHelper>findAll();
	AccountHelper findByName(String name);

	AccountHelper findByUsername(String username);
}

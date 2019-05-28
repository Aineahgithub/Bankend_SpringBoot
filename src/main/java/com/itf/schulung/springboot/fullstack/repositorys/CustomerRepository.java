package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itf.schulung.springboot.fullstack.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findByLastName(String lastName);
	List<Customer> findAll();

}

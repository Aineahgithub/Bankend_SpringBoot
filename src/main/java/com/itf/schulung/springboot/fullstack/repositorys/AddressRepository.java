package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.List;



import org.springframework.data.repository.CrudRepository;

import com.itf.schulung.springboot.fullstack.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
	
	List<Address>findAll();

}

package com.itf.schulung.springboot.fullstack.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itf.schulung.springboot.fullstack.model.Address;
import com.itf.schulung.springboot.fullstack.model.Customer;
import com.itf.schulung.springboot.fullstack.repositorys.AddressRepository;
import com.itf.schulung.springboot.fullstack.repositorys.CustomerRepository;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private CustomerRepository customerRepo;

	@GetMapping(value = "/getAddresses")
	public ResponseEntity<?> getAllAddresses() {
		List<Address> allAddresses = addressRepo.findAll();
		return new ResponseEntity<>(allAddresses, HttpStatus.OK);
	}

	@PostMapping(value = "/postAddress", consumes = "application/json")
	public ResponseEntity<?> postAddress(@RequestBody Address receivedAddress, HttpServletRequest request) {

		Customer customer = customerRepo.findByLastName(request.getParameter("lastName"));
		if (customer == null) {
			return new ResponseEntity<>("No Customer found with lastname: " + request.getParameter("lastName"),
					HttpStatus.BAD_REQUEST);
		}
		receivedAddress.setCustomer_id(customer);
		addressRepo.save(receivedAddress);

		return new ResponseEntity<>(receivedAddress, HttpStatus.OK);

	}

	@PutMapping(value = "/updateAddress/{id}")
	public Address updateAddress(@RequestBody Address address, @PathVariable Integer id)
			throws AccountIdMismatchException, AccountNotFoundException {
		addressRepo.findById(id).orElseThrow(AccountNotFoundException::new).setStreet(address.getStreet());
		address.setCity(address.getCity());

		address.setZipCode(address.getZipCode());
		address.setCountry(address.getCountry());

		return addressRepo.save(address);
	}

}
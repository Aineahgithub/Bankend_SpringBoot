package com.itf.schulung.springboot.fullstack.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itf.schulung.springboot.fullstack.model.Account;
import com.itf.schulung.springboot.fullstack.model.Customer;
import com.itf.schulung.springboot.fullstack.model.Login;
import com.itf.schulung.springboot.fullstack.model.User;
import com.itf.schulung.springboot.fullstack.repositorys.AccountRepository;
import com.itf.schulung.springboot.fullstack.repositorys.CustomerRepository;
import com.itf.schulung.springboot.fullstack.repositorys.LoginRepository;
import com.itf.schulung.springboot.fullstack.repositorys.UserRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	LoginRepository logRepository;

	@GetMapping(value = "/getAccounts")
	public ResponseEntity<?> getAllAccounts() {
		List<Account> allAccounts = accountRepo.findAllByOrderByIdDesc();
		return new ResponseEntity<>(allAccounts, HttpStatus.OK);
	}

	@PostMapping(value = "/postAccounts")
	public ResponseEntity<?> postAccount(@RequestBody Account receivedAccount, HttpServletRequest request, Long id) {
		Customer customer = customerRepo.findByLastName(request.getParameter("lastName"));
		if (customer == null) {
			return new ResponseEntity<>("No Customer found with lastname: " + request.getParameter("lastName"),
					HttpStatus.BAD_REQUEST);
		}
		receivedAccount.setCustomer_id(customer);
		accountRepo.save(receivedAccount);

		Login log = new Login();
		log.setOperation("Account added. ID: " + receivedAccount.getId() + "; Owner: "
				+ receivedAccount.getCustomer_id().getLastName());
		log.setTime(new Timestamp(System.currentTimeMillis()));

		String username = request.getRemoteUser();

		User user = userRepo.findByUsername(username);
		log.setUser(user);

		logRepository.save(log);
		return new ResponseEntity<>(receivedAccount, HttpStatus.OK);
	}
	@GetMapping(value = "/getAccounts/{id}")
	public ResponseEntity<?> getAccountsById(@PathVariable Integer id) {
		Optional<Account> possiblyFoundAccount = accountRepo.findById(id);
		// check if any customer with the provided path variable is present in the
		// database
		if (!possiblyFoundAccount.isPresent()) {
			// No customer was present, we therefore should tell the requester this
			return new ResponseEntity<>("No Account found with ID: " + id, HttpStatus.BAD_REQUEST);
		}
		// we found a customer, therefore we return it
		return new ResponseEntity<>(possiblyFoundAccount.get(), HttpStatus.OK);
	}

}

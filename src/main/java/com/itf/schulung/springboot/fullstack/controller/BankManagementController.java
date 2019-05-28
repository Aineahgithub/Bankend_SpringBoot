package com.itf.schulung.springboot.fullstack.controller;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itf.schulung.springboot.fullstack.model.Account;
import com.itf.schulung.springboot.fullstack.model.Address;
import com.itf.schulung.springboot.fullstack.model.Customer;
import com.itf.schulung.springboot.fullstack.model.Login;
import com.itf.schulung.springboot.fullstack.model.MoneyTransferForm;
import com.itf.schulung.springboot.fullstack.repositorys.AccountRepository;
import com.itf.schulung.springboot.fullstack.repositorys.LoginRepository;
import com.itf.schulung.springboot.fullstack.repositorys.MoneyTransferRepository;
import com.itf.schulung.springboot.fullstack.services.AccountTransactionExpection;
import com.itf.schulung.springboot.fullstack.services.MoneyTransferService;

@RestController
@RequestMapping("/OnlineBanking")
public class BankManagementController {
	@Autowired
	MoneyTransferService moneyservice;
	@Autowired
	MoneyTransferRepository moneyTransferRepo;
	@Autowired
	AccountRepository accountRepo;

	/*
	 * @Autowired LoginRepository loginRepo;
	 * 
	 * 
	 * @CrossOrigin(origins = "*", allowedHeaders = "*")
	 * 
	 * @GetMapping(value = "/getLogins") public ResponseEntity<?> getAllLogins() {
	 * List <Login> logins = (List<Login>) loginRepo.findAll(); return new
	 * ResponseEntity<>(logins, HttpStatus.OK); }
	 * 
	 * @CrossOrigin(origins = "*", allowedHeaders = "*")
	 * 
	 * @PostMapping(value = "/login", consumes = "application/json") public
	 * ResponseEntity<?> Logprocess(@RequestBody Login receivedlog) {
	 * loginRepo.save(receivedlog); return new ResponseEntity<>(receivedlog,
	 * HttpStatus.OK);
	 * 
	 * }
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value = "/transferMoney", consumes = "application/json")
	public ResponseEntity<?> Transferprocess(@RequestBody MoneyTransferForm receivedForm) throws AccountTransactionExpection {
		Account account = accountRepo.findById(receivedForm.getToAccountId()).get();
		Account account2 = accountRepo.findById(receivedForm.getFromAccountId()).get();
		
		 int to= receivedForm.getToAccountId();
		 int from =receivedForm.getFromAccountId();
		 double amount = receivedForm.getAmount();
		receivedForm.setAmount(receivedForm.getAmount());
		
		  receivedForm.setFromAccountId(to);
	       receivedForm.setToAccountId(from);
		 moneyservice.sendMoney(to, from, amount);
		moneyTransferRepo.save(receivedForm);
		return new ResponseEntity<>(receivedForm, HttpStatus.OK);
		
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = "/getTransfers")
	public ResponseEntity<?> getAlltransfers() {
	List <MoneyTransferForm> transfers = (List<MoneyTransferForm>) moneyTransferRepo.findAll();
		return new ResponseEntity<>(transfers, HttpStatus.OK);
	}
	


}

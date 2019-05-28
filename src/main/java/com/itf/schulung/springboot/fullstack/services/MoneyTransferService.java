package com.itf.schulung.springboot.fullstack.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itf.schulung.springboot.fullstack.model.Account;
import com.itf.schulung.springboot.fullstack.repositorys.AccountRepository;
import com.itf.schulung.springboot.fullstack.repositorys.CustomerRepository;

@Service
@Transactional
public class MoneyTransferService {
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerRepository customerRepo;
	public void addMoney(Integer id, double amount) throws AccountTransactionExpection {
		Account account = accountRepo.findById(id).get();
		//Optional<Account> account = accountRepo.findById(id);
		//Accountnr= account.get().getAccountnr();
		//Account account2 = accountRepo.findById(id);
		//Account account1 = (Account) accountRepo.findAllByOrderByIdDesc();
		
		
		if( account==null) {
			throw new AccountTransactionExpection("Account not found"+ id);
		}
		double newBalance = account.getBalance()+ amount;
		if(account.getBalance()+ amount <0) {
			throw new AccountTransactionExpection(" The money in the account"  + id +  "is not enough "+ ""+
		   account.getBalance());
			
		}
		account.setBalance(newBalance);
		//account.get().setDate(date);
		
		
	}
	
	public void sendMoney(int fromAccountId, int toAccountId, double amount) throws AccountTransactionExpection {
		//Date date= new Date();
		addMoney( toAccountId,amount);
		addMoney( fromAccountId, -amount);
	}

}

package com.itf.schulung.springboot.fullstack.Validator;

import com.itf.schulung.springboot.fullstack.model.Account;

public class AccountValidator {
	 public boolean validate(Account account) {
	        return (validateBalance(account.getBalance()) && validateType(account.getType()));
	    }

	    private boolean validateType(String type) {
	        return type.equalsIgnoreCase("Giro") || type.equalsIgnoreCase("Fixed");
	    }
	    private boolean validateBalance(Double balance) {
	        return balance > 0;
	    }
}

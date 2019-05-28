package com.itf.schulung.springboot.fullstack.model;

import java.text.DecimalFormat;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class AccountFactory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	static DecimalFormat my = new DecimalFormat("0.00");

	public static Account getAccount() throws Throwable {
		return new Account(getRandomDeposit(),getRandomAccountnr(),getRandomWithdraw(),getRandomBalance());
		
	}
	public static Account getCustomerAccount() throws Throwable {
		return new Account(getRandomDeposit(),getRandomAccountnr(),getRandomWithdraw(),getRandomBalance());
		
	}
	public static double getRandomBalance() {
		Random b= new Random();
		
		double min = 150.00;
		
		double max=  800.00;
		
		
		return min+ (max-min)*b.nextDouble();
		
	}
	
	public static String getRandomDeposit() {
		 Random r = new Random();
		double c = getRandomBalance();
		double min = 1100;
		double  max= 2000.00;
		
		return my.format((min+  c+ (max-min)*r.nextDouble()));
	}
	
public static String getRandomWithdraw() throws Throwable {
		 Random r = new Random();
		double b = getRandomBalance();
		double min = 600.0;
		double  max= 1200.0;
		 if (b <(min+ (min- max)*r.nextDouble())){
			 throw new Exception("You don't have enough money on your Account!") ;
		 }
		return my.format(b- (min+(max-min)*r.nextDouble()));
	}

public static String getRandomWithdraws(){
	 Random r = new Random();
	double b = getRandomBalance();
	double min = 200.0;
	double  max= 350.0;
	 if (b < max*r.nextDouble()){
		
	 }
	return my.format(min+(max-min)*r.nextDouble());
}
	
public static String getRandomAccountnr() {
		
		Random r = new Random();
	      final String  accountnr= ("10000000123456789"); 
	      final String  countryCode= ("DE23");
	      StringBuilder a = new StringBuilder();
	      a.append(countryCode);
	      for( int i =0; i<= accountnr.length();i++) {
	    	  a.append(accountnr.charAt(r.nextInt(accountnr.length())));
	      }
		return a.toString();
		
	}
}


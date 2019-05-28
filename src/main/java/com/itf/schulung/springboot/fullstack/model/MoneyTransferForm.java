package com.itf.schulung.springboot.fullstack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MoneyTransferForm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Integer toAccountId;
	private Integer fromAccountId;
	private double amount;
	private String message;
	private Date date;

	public MoneyTransferForm(int toAccountId, int fromAccountId, double amount, String message) {
		this.toAccountId = toAccountId;
		this.fromAccountId = fromAccountId;
		this.amount = amount;
		this.message = message;
		this.date = new Date();
	}

	public MoneyTransferForm() {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	/*
	 * public MoneyTransferForm(String to, String from, int i) { // TODO
	 * Auto-generated constructor stub }
	 */
	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int from) {
		this.fromAccountId = from;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package com.itf.schulung.springboot.fullstack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountHelper {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private double salary;
	private String email;
	private String phone;
	@OneToOne
	//@JoinColumn(name = "user_id")
	private User username;

	public AccountHelper() {
	}

	public AccountHelper(int id, String name, double salary, String email, String phone, User username) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.email = email;
		this.phone = phone;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUsername() {
		return username;
	}

	public void setUser(User username) {
		this.username = username;
	}

}

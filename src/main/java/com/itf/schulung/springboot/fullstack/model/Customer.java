package com.itf.schulung.springboot.fullstack.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itf.schulung.springboot.fullstack.Validator.EmailConstraint;
import com.itf.schulung.springboot.fullstack.Validator.NamesConstraint;
import com.itf.schulung.springboot.fullstack.Validator.PhoneContraint;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customer_id;
	@NamesConstraint
	private String firstName;
	@NamesConstraint
	private String lastName;
	@EmailConstraint
	private String email;
	@PhoneContraint
	private String telephone;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date date = new Date();

	public Customer() {
	}

	public Customer(Long customer_id,  String firstName, String lastName, int age, String telephone, String email) {
		this.customer_id= customer_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.telephone = telephone;
		this.email = email;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Customer(String randomlastName, String randomfirstName, String randomAccounts) {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	private Integer age;


	public Integer getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

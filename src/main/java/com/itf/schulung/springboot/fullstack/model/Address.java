package com.itf.schulung.springboot.fullstack.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;

import com.itf.schulung.springboot.fullstack.Validator.NamesConstraint;
import com.itf.schulung.springboot.fullstack.Validator.StreetContraint;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private int id;
	@NamesConstraint
	private String city;
	// @Max(5) @Min(5)
	private String zipCode;
	@NamesConstraint
	String country;
	@NamesConstraint
	private String street;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer_id;

	public Customer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}

	public Address() {
	}

	public Address(int id, String street, String city, 
			String zipCode, String country, Customer customer_id) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
		this.customer_id=customer_id;

	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}

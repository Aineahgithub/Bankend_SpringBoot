package com.itf.schulung.springboot.fullstack.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.Transient;

@Entity
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Timestamp time;
	private String operation;
    @ManyToOne
    private User user;

	public Login() {
	}

	public Login(int id, Timestamp time, String operation ,User user) {
		this.id = id;
		this.time = time;
		this.operation = operation;
		this.user=user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

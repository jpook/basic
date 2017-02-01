package com.pook.feeding.calculator.model.db.entities.impl;

import javax.persistence.Entity;

import com.pook.feeding.calculator.model.db.entities.abstr.AEntity;

@SuppressWarnings("serial")
@Entity
public class User extends AEntity {

	String firstName;
	String lastName;
	String userName;
	String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

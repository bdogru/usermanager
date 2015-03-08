package com.n11.usermanager.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class User {
	@Id
	private String id;

	private String name;
	private String surname;
	private String phone;

	public User() {	}
	
	@PersistenceConstructor
	public User(String name, String surname, String phone) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [name=" + name + " " + surname
				+ ", phone=" + phone + "]";
	}
	
}

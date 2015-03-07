package com.n11.usermanager.domain;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.n11.usermanager.validation.View;

@Document
public class User {
	@Id
	@JsonView(View.Summary.class)
	private String id;

	@JsonView(View.Summary.class)
	private String name;
	@JsonView(View.Summary.class)
	private String surname;
	@JsonView(View.Summary.class)
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

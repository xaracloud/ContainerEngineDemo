package com.hsbc.xaracloud.microservices.customer.model;

import java.util.List;

public class Customer {

	private Integer id;
	private String uid;
	private String name;
	private CustomerType type;
	private List<Account> accounts;

	public Customer() {
		
	}
	
	public Customer(Integer id, String uid, String name, CustomerType type) {
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}

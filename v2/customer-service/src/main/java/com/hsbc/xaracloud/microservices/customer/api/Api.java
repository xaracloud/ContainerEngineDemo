package com.hsbc.xaracloud.microservices.customer.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.xaracloud.microservices.customer.intercomm.AccountClient;
import com.hsbc.xaracloud.microservices.customer.model.Account;
import com.hsbc.xaracloud.microservices.customer.model.Customer;
import com.hsbc.xaracloud.microservices.customer.model.CustomerType;

@RestController
@RequestMapping(Api.REQUEST_MAPPING)
public class Api {
	public static final String REQUEST_MAPPING="/api/v1";
	
	@Autowired
	private AccountClient accountClient;
	
	protected Logger logger = Logger.getLogger(Api.class.getName());
	
	private List<Customer> customers;
	
	public Api() {
		customers = new ArrayList<>();
		customers.add(new Customer(1, "12345", "HSBC", CustomerType.COMPANY));
		customers.add(new Customer(2, "12346", "Alok Ranjan", CustomerType.INDIVIDUAL));
		customers.add(new Customer(3, "12347", "Subhash Todkari", CustomerType.INDIVIDUAL));
		customers.add(new Customer(4, "12348", "Amit Paste", CustomerType.INDIVIDUAL));
	}
	
		
	@RequestMapping("/customers")
	public List<Customer> findAll() {
		logger.info("Customer.findAll()");
		return customers;
	}
	
	@RequestMapping("/customers/uid/{uid}")
	public Customer findByPesel(@PathVariable("uid") String uid) {
		logger.info(String.format("Customer.findByPesel(%s)", uid));
		return customers.stream().filter(it -> it.getUid().equals(uid)).findFirst().get();	
	}
	
	@RequestMapping("/customers/{id}")
	public Customer findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Customer.findById(%s)", id));
		Customer customer = customers.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
		List<Account> accounts =  accountClient.getAccounts(id);
		customer.setAccounts(accounts);
		return customer;
	}
	
}

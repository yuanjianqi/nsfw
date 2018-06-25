package com.yuan.iliya.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.yuan.iliya.entity.Customer;
import com.yuan.iliya.service.CustomerService;

public class CustomerAction extends ActionSupport {
	@Autowired
	private CustomerService customerService;
	private Customer customer;
	
	public String execute(){
		System.out.println(customer);
		customerService.saveCustomer(customer);
		return SUCCESS;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}

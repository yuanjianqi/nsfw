package com.yuan.iliya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuan.iliya.dao.CustomerDao;
import com.yuan.iliya.entity.Customer;
@Service(value = "customerService")
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	public void saveCustomer(Customer customer){
		customerDao.sava(customer);
	}

}

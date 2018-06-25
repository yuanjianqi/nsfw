package com.yuan.iliya.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yuan.iliya.entity.Customer;
@Repository("customerDao")
public class CustomerDao {
	@Autowired
	private SessionFactory sessionFactory;
	public void sava(Customer customer){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		session.close();
	}

}

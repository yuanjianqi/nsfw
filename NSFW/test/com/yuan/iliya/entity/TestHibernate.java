package com.yuan.iliya.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yuan.iliya.guoshui.core.utils.ExecelUtils;


public class TestHibernate {
	
	@Test
	public void testSave(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = applicationContext.getBean("sessionFactory",SessionFactory.class);
		System.out.println(sessionFactory);
		
//		Session session = sessionFactory.openSession();
//		System.out.println(session);
//		session.beginTransaction();
//		Customer customer = new Customer();
//		customer.setName("liming");
//		customer.setPassword("111111");
//		customer.setDesp("可怕");
//		session.save(customer);
//		session.getTransaction().commit();
//		session.close();
//		sessionFactory.close();
	}
	
	@Test
	public void testPOI(){
		ExecelUtils.exportToXls(null, null);
	}
	
	

}

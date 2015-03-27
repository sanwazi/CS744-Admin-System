package com.example.EMR_Admin.authentication.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.authentication.data.Admin;

@Repository
public class AdminAuthenticationDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Admin> listAdmin(){
		Session session = sessionFactory.openSession();
		List<Admin> list = null;
		try {
			list = session.createQuery("from Admin").list();
			System.out.println(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}

package com.example.EMR_Admin.surgery.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.surgery.data.Surgery;

@Repository
public class SurgeryDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<Surgery> serachWithInput(String input) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from Surgery where surgery_name like '"
						+ input + "%'");
		Transaction transaction = session.beginTransaction();
		List<Surgery> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Surgery> getAll(){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Surgery");
		Transaction transaction = session.beginTransaction();
		List<Surgery> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

}

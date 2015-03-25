package com.physician.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.authentication.data.Physician;

@Repository
public class PhysicianDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Physician getPhysicianByAccount(String account) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Physician where account = '"
				+ account + "'");
		Transaction transaction = session.beginTransaction();
		List<Physician> list = q.list();
		transaction.commit();
		session.close();
		Physician p = (Physician) ((list.size() != 0) ? list.remove(0) : null);
		return p;
	}
	
	public List<Physician> serachWithInput( String input ){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Physician where physicianName like '" + input + "%'");
		Transaction transaction = session.beginTransaction();
		List<Physician> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	

}

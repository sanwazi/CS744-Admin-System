package com.example.EMR_Admin.authentication.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.authentication.data.Physician;

@Repository
public class UserAuthenticationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Physician> listUser() {
		Session session = sessionFactory.openSession();
		List<Physician> list = null;
		try {
			list = session.createQuery("from Physician").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public String addUser(Physician user) {
		Session session = sessionFactory.openSession();
		String uid = (String) session.save(user);
		session.close();
		return uid;
	}

	public Physician findPhysicianUserByName(String physicianName) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from Physician where physician_name ='"
				+ physicianName + "'");
		List<Physician> l = q.list();
		transaction.commit();
		session.close();
		if (l.size() > 0) {
			return l.remove(0);
		} else {
			return null;
		}
	}

}

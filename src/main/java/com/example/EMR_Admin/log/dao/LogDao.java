package com.example.EMR_Admin.log.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.log.data.Log;

@Repository
public class LogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Log> getAll() {
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Log");
		Transaction transaction = session.beginTransaction();
		List<Log> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public boolean addLog(Log log) {

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(log);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}
}

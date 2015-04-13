package com.example.EMR_Admin.security_question.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.security_question.data.SecurityQuestion;

@Repository
public class SecurityQuestionDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<SecurityQuestion> getAll(){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from SecurityQuestion");
		Transaction transaction = session.beginTransaction();
		List<SecurityQuestion> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<SecurityQuestion> getById(int sq_id){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from SecurityQuestion where sq_id='"+sq_id+"'");
		Transaction transaction = session.beginTransaction();
		List<SecurityQuestion> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
}

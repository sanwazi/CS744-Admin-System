package com.example.EMR_Admin.security_question_answer_account_relation.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.security_question_answer_account_relation.data.SecurityQuestionAnswerAccountRelation;

@Repository
public class SecurityQuestionAnswerAccountRelationDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<SecurityQuestionAnswerAccountRelation> getAll(){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from SecurityQuestionAnswerAccountRelation");
		Transaction transaction = session.beginTransaction();
		List<SecurityQuestionAnswerAccountRelation> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<SecurityQuestionAnswerAccountRelation> getByAdminAccount(String admin_account){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from SecurityQuestionAnswerAccountRelation where admin_account = '"+admin_account+"'");
		Transaction transaction = session.beginTransaction();
		List<SecurityQuestionAnswerAccountRelation> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
}

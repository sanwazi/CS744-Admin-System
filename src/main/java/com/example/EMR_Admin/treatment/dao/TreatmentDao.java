package com.example.EMR_Admin.treatment.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.treatment.data.Treatment;

@Repository
public class TreatmentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Treatment> findAll(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from Treatment");
		List<Treatment> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public Treatment getTreatmentById( int id ){
		List<Treatment> list = findAll();
		for( Treatment t: list )
			if( t.getTreatment_id() == id )
				return t;
		return null;
	}
	
	public boolean addTreatment( Treatment t ){

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteTreatment( Treatment t ){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateTreatment( Treatment t ){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(t);
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

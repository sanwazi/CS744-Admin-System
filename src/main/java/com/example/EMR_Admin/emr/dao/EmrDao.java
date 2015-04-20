package com.example.EMR_Admin.emr.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.emr.data.Emr;

@Repository
public class EmrDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Emr findEmrByPatientId(int patientId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Emr where patient_id = "
				+ patientId);
		Transaction transaction = session.beginTransaction();
		List<Emr> list = q.list();
		transaction.commit();
		session.close();
		Emr emr = (Emr) ((list.size() != 0) ? list.remove(0) : null);
		return emr;
	}
	
	public boolean insertNewEmr(Emr emr){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(emr);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateEmr(Emr emr){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(emr);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Emr> getAll(){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Emr");
		Transaction transaction = session.beginTransaction();
		List<Emr> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
}

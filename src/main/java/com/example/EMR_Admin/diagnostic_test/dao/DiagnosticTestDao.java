package com.example.EMR_Admin.diagnostic_test.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.diagnostic_test.data.DiagnosticTest;
import com.example.EMR_Admin.drug.data.Drug;

@Repository
public class DiagnosticTestDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<DiagnosticTest> serachWithInput(String input) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from DiagnosticTest where diagnostic_test_name like '"
						+ input + "%'");
		Transaction transaction = session.beginTransaction();
		List<DiagnosticTest> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<DiagnosticTest> findAll(){
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from DiagnosticTest");
		Transaction transaction = session.beginTransaction();
		List<DiagnosticTest> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public List<DiagnosticTest> getDiagnosticByName(String dtName){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from DiagnosticTest where diagnostic_test_name = '"+dtName+"'");
		Transaction transaction = session.beginTransaction();
		List<DiagnosticTest> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<DiagnosticTest> getDiagnosticById(int dt_id){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from DiagnosticTest where diagnostic_test_id = '"+dt_id+"'");
		Transaction transaction = session.beginTransaction();
		List<DiagnosticTest> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public boolean addDiagnostic(DiagnosticTest dt){
		if(getDiagnosticByName(dt.getDiagnostic_test_name()).isEmpty()){
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(dt);
				session.getTransaction().commit();
				session.close();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
	public boolean deleteDiagnostic(DiagnosticTest dt){
		if(!getDiagnosticByName(dt.getDiagnostic_test_name()).isEmpty()){
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(dt);
				session.getTransaction().commit();
				session.close();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	public DiagnosticTest findDiagnosticById(int dt_id) {
		// TODO Auto-generated method stub
		
		if(!getDiagnosticById(dt_id).isEmpty()){
			DiagnosticTest dt = getDiagnosticById(dt_id).get(0);
			return dt;
		}
		return null;
	}
}

package com.example.EMR_Admin.drug.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.drug.data.Drug;

@Repository
public class DrugDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Drug> searchWithInput(String input) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Drug where drug_name like '"
				+ input +"%'");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Drug> getAll(){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Drug");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Drug> getDrugByName(String drugName){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Drug where drug_name = '"+drugName+"'");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public boolean addDrug(Drug drug){
		if(getDrugByName(drug.getDrug_name()).isEmpty()){
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(drug);
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
}

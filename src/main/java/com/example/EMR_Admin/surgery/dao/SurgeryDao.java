package com.example.EMR_Admin.surgery.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.drug.data.Drug;
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
	
	public List<Drug> getByName(String surgeryName){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Surgery where surgery_name = '"+surgeryName+"'");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Surgery> getById(int id){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Surgery where surgery_id='"+id+"'");
		Transaction transaction = session.beginTransaction();
		List<Surgery> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public boolean addSurgery(Surgery surgery){
		if(getByName(surgery.getSurgery_name()).isEmpty()){
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(surgery);
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
	
	public boolean updateSurgery(Surgery surgery){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(surgery);
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

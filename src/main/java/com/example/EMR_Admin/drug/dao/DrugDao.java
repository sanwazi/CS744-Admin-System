package com.example.EMR_Admin.drug.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.emr.data.Emr;

@Repository
public class DrugDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Drug> searchWithInput(String input) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Drug where drug_name like '"
				+ input + "%'");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public List<Drug> getAll() {
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Drug");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public List<Drug> getDrugByName(String drug_name_medical) {
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Drug where drug_name_medical = '"
				+ drug_name_medical + "'");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public List<Drug> getDrugById(int drug_id) {
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Drug where drug_id = '" + drug_id
				+ "'");
		Transaction transaction = session.beginTransaction();
		List<Drug> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public boolean addDrug(Drug drug) {

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

	public boolean updateDrug(Drug drug) {

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(drug);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean deleteDrugById(int drug_id) {

		try {
			Session session = sessionFactory.openSession();
			Query q = session.createQuery("delete Drug where drug_id = '"
					+ drug_id + "'");
			q.executeUpdate();
			session.beginTransaction();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean addListDrug(List<Drug> input) {

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			for (Drug d : input) {

				session.saveOrUpdate(d);

			}
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

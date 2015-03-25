package com.drug.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.drug.data.Drug;
import com.emr.data.Emr;

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
}

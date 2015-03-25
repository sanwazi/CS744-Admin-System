package com.relation_prescription_drug.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prescription.data.Prescription;
import com.relation_prescription_drug.data.PrescriptionDrugRelation;

@Repository
public class PrescriptionDrugDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<PrescriptionDrugRelation> getByPrescriptionId(int prescriptionId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from PrescriptionDrugRelation where prescription_id="
						+ prescriptionId);
		Transaction transaction = session.beginTransaction();
		List<PrescriptionDrugRelation> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public void save(PrescriptionDrugRelation pdr) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(pdr);
		session.getTransaction().commit();
		session.close();
	}

}

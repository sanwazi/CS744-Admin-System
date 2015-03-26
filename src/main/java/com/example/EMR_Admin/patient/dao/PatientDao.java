package com.example.EMR_Admin.patient.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.patient.data.*;

@Repository
public class PatientDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Patient findPatientByPatientId(int patientId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Patient where patient_id = "
				+ patientId);
		Transaction transaction = session.beginTransaction();
		List<Patient> list = q.list();
		transaction.commit();
		session.close();
		Patient patient = (Patient) ((list.size() != 0) ? list.remove(0) : null);
		return patient;
	}
}
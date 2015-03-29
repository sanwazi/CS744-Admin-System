package com.example.EMR_Admin.patient_physician_relation.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.patient_physician_relation.data.RelationPhysicianPatient;
import com.constant.ConstantValue;

@Repository
public class PatientPhysicianRelationDao implements
		IPatientPhysicianRelationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<RelationPhysicianPatient> find() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from RelationPhysicianPatient");
		Transaction transaction = session.beginTransaction();
		List<RelationPhysicianPatient> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	@Override
	public List<RelationPhysicianPatient> findTemporaryCareByPatientId(
			int patient_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from RelationPhysicianPatient where patient_id = "
						+ patient_id
						+ " and relation_type = '"
						+ ConstantValue.TEMPORARY_CARE_RELATION + "'");
		Transaction transaction = session.beginTransaction();
		List<RelationPhysicianPatient> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public List<RelationPhysicianPatient> findPrimaryCareRelation(
			int physicianId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from RelationPhysicianPatient where relation_type='"
						+ ConstantValue.PRIMARY_CARE_RELATION
						+ "' and physician_id=" + physicianId);
		Transaction transaction = session.beginTransaction();
		List<RelationPhysicianPatient> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public List<RelationPhysicianPatient> findTemporaryCareRelation(
			int physicianId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from RelationPhysicianPatient where relation_type='"
						+ ConstantValue.TEMPORARY_CARE_RELATION
						+ "' and physician_id=" + physicianId);
		Transaction transaction = session.beginTransaction();
		List<RelationPhysicianPatient> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

}

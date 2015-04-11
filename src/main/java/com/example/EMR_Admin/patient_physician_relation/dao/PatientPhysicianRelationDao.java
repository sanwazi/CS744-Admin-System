package com.example.EMR_Admin.patient_physician_relation.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.patient_physician_relation.data.RelationPhysicianPatient;
import com.example.EMR_Admin.surgery.data.Surgery;
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
	
	public boolean addPhysicianPatientRelation( RelationPhysicianPatient relation ){
		if( findPrimaryRelationByPatientId(relation.getPatient_id()).isEmpty() ){
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(relation);
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

	public List<RelationPhysicianPatient> findPrimaryRelationByPatientId( int patientId ){
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from RelationPhysicianPatient where relation_type='"
						+ ConstantValue.PRIMARY_CARE_RELATION
						+ "' and patient_id=" + patientId);
		Transaction transaction = session.beginTransaction();
		List<RelationPhysicianPatient> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public boolean deleteRelation( RelationPhysicianPatient relation ){
		if( findRelationByRelationId(relation.getRelation_id()) != null ){
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(relation);
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
	
	public RelationPhysicianPatient findRelationByRelationId( int relationId ){
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from RelationPhysicianPatient where relation_id='" + relationId +"'");
		Transaction transaction = session.beginTransaction();
		List<RelationPhysicianPatient> list = q.list();
		transaction.commit();
		session.close();
		return list.isEmpty()? null: list.get(0)  ;
	}
	
	public boolean updateRelation(RelationPhysicianPatient relation){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(relation);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addRelation(RelationPhysicianPatient relation){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(relation);
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

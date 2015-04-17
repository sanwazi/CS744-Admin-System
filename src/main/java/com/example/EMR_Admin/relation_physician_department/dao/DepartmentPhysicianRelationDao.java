package com.example.EMR_Admin.relation_physician_department.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.physician.service.PhysicianService;
import com.example.EMR_Admin.relation_physician_department.data.DepartmentPhysicianRelation;

@Repository
public class DepartmentPhysicianRelationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private PhysicianService phyService;
	
	public List<DepartmentPhysicianRelation> getRelationByDepartmentName(String dName){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from DepartmentPhysicianRelation where department_name = '"
				+ dName + "'");
		Transaction transaction = session.beginTransaction();
		List<DepartmentPhysicianRelation> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Physician> getNonRegisteredPhysiciansByInputPhysicianName(String input){
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from Physician where physicianName like '"
						+ input + "%' and physicianId not in (select physician_id from DepartmentPhysicianRelation)");
		Transaction transaction = session.beginTransaction();
		List<Physician> list = q.list();
		transaction.commit();
		session.close();
		return list;
		
	}
	
	public List<DepartmentPhysicianRelation> getRelationByPhysicianName(String physician_name){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from DepartmentPhysicianRelation where physician_name = '"
				+ physician_name + "'");
		Transaction transaction = session.beginTransaction();
		List<DepartmentPhysicianRelation> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public String addRelation(DepartmentPhysicianRelation inputRelation){
		String physician_name = inputRelation.getPhysician_name();
		if (getRelationByPhysicianName(physician_name).isEmpty()) {
			if (!phyService.getPhysicianByName(physician_name).isEmpty()) {
				try {
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					session.save(inputRelation);
					session.getTransaction().commit();
					session.close();
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "addingToDbfalse";
				}
				return "addingToDbSuccess";
			}
			return "noSuchPhysician";
		}
		return "relationExists";
	}
	
	public DepartmentPhysicianRelation getRelationByPhysicianId(int physician_id){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from DepartmentPhysicianRelation where physician_id = '"
				+ physician_id + "'");
		Transaction transaction = session.beginTransaction();
		List<DepartmentPhysicianRelation> list = q.list();
		transaction.commit();
		session.close();
		return list.get(0);
	}
	
	public boolean deleteRelationByPhysicianId(int physician_id){
		if(getRelationByPhysicianId(physician_id) != null){
			try {
				Session session = sessionFactory.openSession();
				Query q = session.createQuery("delete DepartmentPhysicianRelation where physician_id = '"+physician_id+"'");
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
		return false;
	}
	
}

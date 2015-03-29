package com.example.EMR_Admin.relation_physician_department.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.relation_physician_department.data.DepartmentPhysicianRelation;

@Repository
public class DepartmentPhysicianRelationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
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
	
}

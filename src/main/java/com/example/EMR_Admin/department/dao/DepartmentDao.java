package com.example.EMR_Admin.department.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.department.data.Department;
import com.example.EMR_Admin.authentication.data.Physician;

@Repository
public class DepartmentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Department> serachWithInput(String input) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from Department where department_name like '"
						+ input + "%'");
		Transaction transaction = session.beginTransaction();
		List<Department> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Department> displayAll(){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Department");
		Transaction transaction = session.beginTransaction();
		List<Department> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	//add
	public boolean addDepartment(){
		return true;
	}
	
	//getIdbyName
	public int getIdByDepartmentName(String dName){
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Department where department_name='"+dName+"'");
		Transaction transaction = session.beginTransaction();
		List<Department> list = q.list();
		transaction.commit();
		session.close();
		return list.get(0).getDepartment_id();
	}

}

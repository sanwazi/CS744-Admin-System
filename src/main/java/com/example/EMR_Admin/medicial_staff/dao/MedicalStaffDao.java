package com.example.EMR_Admin.medicial_staff.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.medical_staff.data.MedicalStaff;


@Repository
public class MedicalStaffDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<MedicalStaff> listMedicalStaff(){
		Session session = sessionFactory.openSession();
		List<MedicalStaff> list = null;
		try {
			list = session.createQuery("from MedicalStaff").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}

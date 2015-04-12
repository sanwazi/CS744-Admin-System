package com.example.EMR_Admin.medicial_staff.dao;

import java.util.List;

import org.hibernate.HibernateException;
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
	
	public MedicalStaff findMSbyID( int ms_id ){
		List<MedicalStaff> list = listMedicalStaff();
		for( MedicalStaff ms: list )
			if( ms.getMs_id() == ms_id )
				return ms;
		return null;
	}
	
	public boolean updaateMS( MedicalStaff ms ){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(ms);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteMS( MedicalStaff ms ){
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(ms);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addMS( MedicalStaff newMS ){
		List<MedicalStaff> list = listMedicalStaff();
		for( MedicalStaff ms: list )
			if( ms.getPhysician_id() == newMS.getPhysician_id() && ms.getMs_name().equals(newMS.getMs_name()) )
				return false;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(newMS);
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

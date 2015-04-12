package com.example.EMR_Admin.authentication.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.EMR_Admin.authentication.data.Admin;

@Repository
public class AdminAuthenticationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Admin> listAdmin() {
		Session session = sessionFactory.openSession();
		List<Admin> list = null;
		try {
			list = session.createQuery("from Admin").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public boolean addAdmin(Admin admin) {
		if (!listAdmin().contains(admin)) {
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(admin);
				session.getTransaction().commit();
				session.close();
			} catch (HibernateException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean deleteAdmin(Admin admin) {

		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(admin);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean updateAdmin(Admin admin) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(admin);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Admin findAdminById(int id) {
		List<Admin> list = listAdmin();
		for (Admin a : list)
			if (a.getAdminId() == id)
				return a;
		return null;
	}
}

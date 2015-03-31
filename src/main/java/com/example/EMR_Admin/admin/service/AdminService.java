package com.example.EMR_Admin.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.authentication.dao.AdminAuthenticationDao;
import com.example.EMR_Admin.authentication.data.Admin;

@Service
public class AdminService {

	@Autowired
	AdminAuthenticationDao ADao;
	
	public List<Admin> getAdminList() {
		// TODO Auto-generated method stub
		List<Admin> list = ADao.listAdmin();
		return list;
	}
	
	public boolean addAdmin( Admin admin ){
		return ADao.addAdmin(admin);
	}
	
	public boolean deleteAdmin( Admin admin ){
		return ADao.deleteAdmin(admin);
	}
	
	public boolean updateAdmin( Admin admin ){
		return ADao.updateAdmin(admin);
		
	}
}

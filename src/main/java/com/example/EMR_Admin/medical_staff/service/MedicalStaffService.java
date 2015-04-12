package com.example.EMR_Admin.medical_staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.medical_staff.data.MedicalStaff;
import com.example.EMR_Admin.medicial_staff.dao.MedicalStaffDao;

@Service
public class MedicalStaffService {

	@Autowired
	MedicalStaffDao msDao;
	
	public List<MedicalStaff> medicalStaffList( ){
		return msDao.listMedicalStaff();
	}
	
	public MedicalStaff getMSByID( int id ){
		return msDao.findMSbyID(id);
	}
	
	public boolean addMS( MedicalStaff ms ){
		return msDao.addMS(ms);
	}
	
	public boolean updateMS( MedicalStaff ms ){
		return msDao.updaateMS(ms);
	}
	
	public boolean deleteMS( MedicalStaff ms ){
		return msDao.deleteMS( ms );
	}
}

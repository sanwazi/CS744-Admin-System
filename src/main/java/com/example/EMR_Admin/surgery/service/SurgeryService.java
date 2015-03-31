package com.example.EMR_Admin.surgery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.surgery.dao.SurgeryDao;
import com.example.EMR_Admin.surgery.data.Surgery;

@Service
public class SurgeryService {

	@Autowired
	SurgeryDao sDao;

	public List<Surgery> searchWithInput(String input) {
		// TODO Auto-generated method stub
		return sDao.serachWithInput(input);
	}
	
	public List<Surgery> getAll(){
		return sDao.getAll();
	}
	
	public List<Surgery> getById(int surgery_id){
		return sDao.getById(surgery_id);
	}
	
	public boolean addSurgery(Surgery surgery){
		return sDao.addSurgery(surgery);
	}
	
	public boolean updateSurgery(Surgery surgery){
		return sDao.updateSurgery(surgery);
	}
	
	public boolean deleteSurgery(Surgery surgery){
		int surgery_id = surgery.getSurgery_id();
		return sDao.deleteSurgeryById(surgery_id);
	}
}

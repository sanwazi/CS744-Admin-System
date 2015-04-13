package com.example.EMR_Admin.treatment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.treatment.dao.TreatmentDao;
import com.example.EMR_Admin.treatment.data.Treatment;

@Service
public class TreatmentService {

	@Autowired
	TreatmentDao tDao;
	
	
	public List<Treatment> findAll(){
		return tDao.findAll();
	}
	
	public Treatment findTreatmentById( int id ){
		return tDao.getTreatmentById(id);
	}
	
	public boolean addTreatment( Treatment t ){
		return tDao.addTreatment( t); 
	}
	
	public boolean deleteTreatment( Treatment t ){
		return tDao.deleteTreatment( t );
	}
	
	public boolean updateTreatment( Treatment t ){
		return tDao.updateTreatment(t);
	}
}

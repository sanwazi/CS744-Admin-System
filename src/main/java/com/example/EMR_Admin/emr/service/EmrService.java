package com.example.EMR_Admin.emr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.emr.dao.EmrDao;
import com.example.EMR_Admin.emr.data.Emr;

@Service
public class EmrService {

	@Autowired
	EmrDao eDao;

	public Emr getEmrByPatientId(int patientId) {
		// TODO Auto-generated method stub
		Emr emr = eDao.findEmrByPatientId(patientId);
		return emr;
	}

	public boolean createNewEmr(Emr emr) {
		return eDao.insertNewEmr(emr);

	}

	public boolean updateEmr(Emr emr) {
		return eDao.updateEmr(emr);
	}
	
	public List<Emr> getAll(){
		return eDao.getAll();
	}
}

package com.emr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emr.dao.EmrDao;
import com.emr.data.Emr;

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
}

package com.prescription.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prescription.dao.PrescriptionDao;
import com.prescription.data.Prescription;

@Service
public class PrescriptionService {

	@Autowired
	PrescriptionDao pDao;

	public List<Prescription> getByTranscriptionId(int transcriptionId) {
		// TODO Auto-generated method stub
		return pDao.getByTranscriptionId(transcriptionId);
	}

	public String save(Prescription p) {
		// TODO Auto-generated method stub
		return pDao.save(p);
	}

}

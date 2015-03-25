package com.relation_prescription_drug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relation_prescription_drug.dao.PrescriptionDrugDao;
import com.relation_prescription_drug.data.PrescriptionDrugRelation;

@Service
public class PrescriptionDrugService {

	@Autowired
	PrescriptionDrugDao pdDao;

	public List<PrescriptionDrugRelation> getByPrescriptionId(int prescriptionId) {
		// TODO Auto-generated method stub
		return pdDao.getByPrescriptionId(prescriptionId);

	}

	public void save(PrescriptionDrugRelation pdr) {
		// TODO Auto-generated method stub
		pdDao.save(pdr);
	}

}

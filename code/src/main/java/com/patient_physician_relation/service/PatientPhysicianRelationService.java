package com.patient_physician_relation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.data.Physician;
import com.constant.ConstantValue;
import com.patient_physician_relation.dao.PatientPhysicianRelationDao;
import com.patient_physician_relation.data.RelationPhysicianPatient;
import com.physician.service.PhysicianService;

@Service
public class PatientPhysicianRelationService implements
		IPatientPhysicianRelationService {

	@Autowired
	PhysicianService pService;
	@Autowired
	PatientPhysicianRelationDao ppRelationDao;

	Physician currentPhysician;

	public List<RelationPhysicianPatient> findPrimaryCaraPatient() {
		// TODO Auto-generated method stub
		List<RelationPhysicianPatient> primaryCarePatients = ppRelationDao
				.findPrimaryCareRelation(getCurrentPhysician().getPhysicianId());
		return primaryCarePatients;
	}

	@Override
	public List<RelationPhysicianPatient> findTemporaryCaraPatient() {
		// TODO Auto-generated method stub
		List<RelationPhysicianPatient> temporaryCarePatients = ppRelationDao
				.findTemporaryCareRelation(getCurrentPhysician()
						.getPhysicianId());

		return temporaryCarePatients;
	}

	private Physician getCurrentPhysician() {
		currentPhysician = pService.currentPhysician();
		return currentPhysician;
	}

	@Override
	public List<RelationPhysicianPatient> findTemporaryCaraByPatientId(
			int patient_id) {
		// TODO Auto-generated method stub
		List<RelationPhysicianPatient> temporaryCarePatients = ppRelationDao
				.findTemporaryCareByPatientId(patient_id);
		return temporaryCarePatients;
	}

}

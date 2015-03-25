package com.patient_physician_relation.service;

import java.util.List;

import com.patient_physician_relation.data.RelationPhysicianPatient;

public interface IPatientPhysicianRelationService {
	public List<RelationPhysicianPatient> findPrimaryCaraPatient();
	public List<RelationPhysicianPatient> findTemporaryCaraPatient();
	public List<RelationPhysicianPatient> findTemporaryCaraByPatientId( int patient_id);
}

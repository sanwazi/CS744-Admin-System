package com.example.EMR_Admin.patient_physician_relation.service;

import java.util.List;

import com.example.EMR_Admin.patient_physician_relation.data.RelationPhysicianPatient;

public interface IPatientPhysicianRelationService {
	public List<RelationPhysicianPatient> findPrimaryCaraPatient();
	public List<RelationPhysicianPatient> findTemporaryCaraPatient();
	public List<RelationPhysicianPatient> findTemporaryCaraByPatientId( int patient_id);
}

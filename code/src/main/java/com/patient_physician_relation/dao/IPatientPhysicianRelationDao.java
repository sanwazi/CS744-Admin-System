package com.patient_physician_relation.dao;

import java.util.List;

import com.patient_physician_relation.data.RelationPhysicianPatient;

public interface IPatientPhysicianRelationDao {
	public List<RelationPhysicianPatient> find();
	public List<RelationPhysicianPatient> findTemporaryCareByPatientId( int patient_id);
}

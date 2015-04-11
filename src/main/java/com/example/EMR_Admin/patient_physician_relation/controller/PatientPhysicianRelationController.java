package com.example.EMR_Admin.patient_physician_relation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.patient.data.Patient;
import com.example.EMR_Admin.patient.service.PatientService;
import com.example.EMR_Admin.patient_physician_relation.data.RelationPhysicianPatient;
import com.example.EMR_Admin.patient_physician_relation.service.PatientPhysicianRelationService;
import com.example.EMR_Admin.physician.service.PhysicianService;

@Controller
public class PatientPhysicianRelationController {

	@Autowired
	PatientPhysicianRelationService pService;

	@Autowired
	PatientService patientService;

	@Autowired
	PhysicianService physicianService;

	@RequestMapping(value = "/patient", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody Map<String, List<RelationPhysicianPatient>> patient() {
		List<RelationPhysicianPatient> primary = pService
				.findPrimaryCaraPatient();
		List<RelationPhysicianPatient> temporary = pService
				.findTemporaryCaraPatient();
		HashMap<String, List<RelationPhysicianPatient>> map = new HashMap<String, List<RelationPhysicianPatient>>();
		map.put("primary", primary);
		map.put("temporary", temporary);
		return map;
	}

	@RequestMapping(value = "/relation/temporaryCare", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody List<RelationPhysicianPatient> findTemporaryCareByPatientId(
			@RequestParam(value = "patient_id", required = true) int patient_id) {

		List<RelationPhysicianPatient> temporary = pService
				.findTemporaryCaraByPatientId(patient_id);
		return temporary;
	}

	@RequestMapping(value = "/relation/addPrimaryRelation", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody String addPrimaryRelation(
			@RequestParam (value="patient_name", required = true) String patientName,
			@RequestParam (value="physician_name", required = true ) String physicianName,
			@RequestParam (value="physician_id", required = true ) String physicianId,
			@RequestParam (value="patient_id", required = true) String patientId ) {
		
		List<Patient> patients = patientService.patientList();
		boolean patientValidate = false , physicianValidate = false;
		for( Patient p : patients )
			if( p.getPatient_name().equals(patientName) && p.getPatient_id() == Integer.parseInt(patientId))
				patientValidate = true;
		if( !patientValidate )
			return "patientNameWrong";
		
		List<Physician> physicians = physicianService.getPhysicianList();
		for( Physician p : physicians )
			if( p.getPhysicianName().equals( physicianName) && p.getPhysicianId() == Integer.parseInt(physicianId))
				physicianValidate = true;
		if( !physicianValidate )
			return "physicianNameWrong";
		
		List<RelationPhysicianPatient> relations = pService.findAllRelation();
		for( RelationPhysicianPatient r : relations )
			if( r.getPatient_id() == Integer.parseInt(patientId) && r.getRelation_type().equals("PRIMARY_CARE"))
				return "hasPhysician";
		
		RelationPhysicianPatient relation = new RelationPhysicianPatient();
		List<Integer> physicianIds = new ArrayList<Integer>();
		physicianIds.add(Integer.parseInt(physicianId));
		Physician physician = physicianService.getPhysiciansByIds( physicianIds).get(0);
		Patient patient = patientService.getPatientById( Integer.parseInt(patientId));
		relation.setPhysician_id(physician.getPhysicianId());
		relation.setPhysician_name(physician.getPhysicianName());
		relation.setPatient_id(patient.getPatient_id());
		relation.setPatient_name(patient.getPatient_name());
		relation.setPatient_gender(patient.getPatient_gender());
		relation.setPatient_birthday(patient.getPatient_birthday());
		relation.setRelation_type("PRIMARY_CARE");
		relation.setPrimary_physician_name(physician.getPhysicianName());
		relation.setAccess_right("11");
		relation.setPhysician_id(1);
		relation.setStart_date( new Date());
		relation.setEnd_date( new Date(2018,10,2));
		
		boolean result = pService.addRelation(relation);
		if( result )
			return "s";
		else
			return "d";
	}
			
	

	@RequestMapping(value = "/relation/physician_patient_list", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<RelationPhysicianPatient> findAllRelation() {

		List<RelationPhysicianPatient> list = pService.findAllRelation();
		return list;
	}

	@RequestMapping(value = "/relation/validatePatient", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody boolean validatePatientByPatienName(
			@RequestParam(value = "patient_name", required = true) String name) {

		List<RelationPhysicianPatient> list = pService.findAllRelation();
		for (RelationPhysicianPatient relation : list) {
			if (relation.getPatient_name().equals(name))
				return false;
		}
		return true;
	}


	@RequestMapping(value = "/relation/getRelationById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody RelationPhysicianPatient getRelationById(
			@RequestParam(value = "relation_id", required = true) int relation_id) {
		return pService.findRelationById(relation_id);
	}

	@RequestMapping(value = "/relation/deleteRelation", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String deleteRelation(
			@RequestParam(value = "relation_id", required = true) int relation_id) {
		
		RelationPhysicianPatient relation = pService.findRelationById( relation_id );
		boolean result = pService.deleteRelation( relation );
		if (result)
			return "s";
		else
			return "d";
	}
}

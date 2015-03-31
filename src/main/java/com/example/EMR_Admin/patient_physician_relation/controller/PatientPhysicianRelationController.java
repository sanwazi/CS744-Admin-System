package com.example.EMR_Admin.patient_physician_relation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.patient_physician_relation.data.RelationPhysicianPatient;
import com.example.EMR_Admin.patient_physician_relation.service.PatientPhysicianRelationService;
import com.example.EMR_Admin.surgery.data.Surgery;

@Controller
public class PatientPhysicianRelationController {

	@Autowired
	PatientPhysicianRelationService pService;

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
				.findTemporaryCaraByPatientId( patient_id);
		return temporary;
	}
	
	@RequestMapping(value = "/relation/physician_patient_list", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<RelationPhysicianPatient> findAllRelation(
			) {

		List<RelationPhysicianPatient> list = pService
				.findAllRelation();
		return list;
	}
	
	//??????????
	@RequestMapping(value = "/relation/updateRelation", method = RequestMethod.GET)
	@Secured(value = {"ROLE_ADMIN"})
	public @ResponseBody String updateRelation(
			@RequestParam(value = "relation_id", required=true) int relation_id){
		RelationPhysicianPatient relation = new RelationPhysicianPatient();
		relation.setRelation_id(relation_id);
		boolean result = pService.updateRelation(relation);
		if(result) return "s";
		else return "d";
	}

}

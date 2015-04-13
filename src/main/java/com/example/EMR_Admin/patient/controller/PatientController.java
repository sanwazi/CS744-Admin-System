package com.example.EMR_Admin.patient.controller;

import java.util.ArrayList;
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

import com.example.EMR_Admin.patient.data.Patient;
import com.example.EMR_Admin.patient.service.PatientService;

@Controller
public class PatientController {
	
	@Autowired 
	PatientService pService;
	
	@RequestMapping(value = "/patientList", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<HashMap<String, String>> patientList(
			) {
		HashMap<String, String> map;
		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		List<Patient> list  = pService.patientList();
		
		for( Patient patient : list ){
			map = new HashMap<String,String>();
			map.put("patient_name", patient.getPatient_name());
			map.put("patient_gender", patient.getPatient_gender());
			map.put("patient_age", pService.computeAge(patient.getPatient_birthday()));
			map.put("patient_birthday", pService.dateToString(patient.getPatient_birthday()));
			map.put("patient_ssn", patient.getSsn());
			result.add(map);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/patient/validatePatientName", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody boolean validatePatientName(
			@RequestParam(value = "patientName", required = true) String input) {
		List<Patient> list = pService.patientList();
		for( Patient p: list )
			if( p.getPatient_name().equals(input))
				return true;
		return false;
	}
	
	@RequestMapping(value = "/patient/autocompleteWithFullName", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<HashMap<String,String>> searchWithInputFullNanme(
			@RequestParam(value = "input", required = true) String input) {
		List<HashMap<String,String>> res = new ArrayList<HashMap<String,String>>();
		List<Patient> list = pService.searchWithInputFullName(input);
		for( Patient p: list ){
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("lable", String.valueOf(p.getPatient_id()));
			map.put("value", p.getPatient_name());
			res.add(map);
		}
		return res;
	}
	
	@RequestMapping(value = "/patient/getPatientById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody Map<String, String> getPatientById(
			@RequestParam(value = "patient_id", required = true) int patientId) {
		HashMap<String, String> map = new HashMap<String, String>();
		Patient patient = pService.getPatientById(patientId);
		
		if (patient != null) {
			map.put("patient_id", patientId+"");
			map.put("patient_name", patient.getPatient_name());
			map.put("patient_gender", patient.getPatient_gender());
			map.put("patient_birthday", pService.dateToString(patient.getPatient_birthday()));
			map.put("patient_age", pService.computeAge(patient.getPatient_birthday()));
			map.put("patient_ssn", patient.getSsn());
		} else {
			map = null;
		}
		return map;
	}
}

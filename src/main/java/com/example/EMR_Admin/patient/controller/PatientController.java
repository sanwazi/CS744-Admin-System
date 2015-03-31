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
			result.add(map);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/patient/autocomplete", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Patient> searchWithInput(
			@RequestParam(value = "input", required = true) String input) {
		List<Patient> list = pService.searchWithInput(input);
		return list;
	}
}

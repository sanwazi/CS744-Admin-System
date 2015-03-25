package com.prescription.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authentication.data.Physician;
import com.patient.data.Patient;
import com.patient.service.PatientService;
import com.physician.service.PhysicianService;
import com.prescription.data.Prescription;
import com.prescription.service.PrescriptionService;

@Controller
public class PrescriptionController {

	@Autowired
	PrescriptionService pService;
	@Autowired
	PatientService patientService;
	@Autowired
	PhysicianService physicianService;
	
	@RequestMapping(value = "/prescription/list", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody List<Prescription> list(
			@RequestParam(value = "transcription_id", required = true) int transcriptionId) {
		return pService.getByTranscriptionId(transcriptionId);
	}

	@RequestMapping(value = "/prescription/add", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody String add(
			@RequestParam(value = "transcriptionId", required = true) int transcriptionId,
			@RequestParam(value = "patientId", required = true) int patientId) {
		
		Prescription p = new Prescription();
		p.setTranscription_id(transcriptionId);
		p.setPatient_id(patientId);
		Patient patient = patientService.getPatientById(patientId); 
		p.setPatient_name(patient.getPatient_name());
		Physician physician = physicianService.currentPhysician();
		p.setPhysician_id(physician.getPhysicianId());
		p.setPhysician_name(physician.getPhysicianName());
		p.setCreate_date(new Date());
		p.setContent("");
		return pService.save(p);
	}
}

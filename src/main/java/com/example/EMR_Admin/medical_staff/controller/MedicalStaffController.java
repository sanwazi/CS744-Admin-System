package com.example.EMR_Admin.medical_staff.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.medical_staff.data.MedicalStaff;
import com.example.EMR_Admin.medical_staff.service.MedicalStaffService;
import com.example.EMR_Admin.physician.service.PhysicianService;

@Controller
public class MedicalStaffController {

	@Autowired
	MedicalStaffService MedicalStaffService;
	
	@Autowired
	PhysicianService pService;

	@RequestMapping(value = "/medicalstaff/list", method = RequestMethod.GET)
	@Secured(value = { "ROLE_Admin" })
	public @ResponseBody List<MedicalStaff> getMedicalStaffList() {
		List<MedicalStaff> list = MedicalStaffService.medicalStaffList();
//		List<MedicalStaff> res = new ArrayList<MedicalStaff>();
//		for( MedicalStaff ms: list ){
//			
//		}
		return list;
	}
	
	@RequestMapping(value = "/relation/physician_medical_staff", method = RequestMethod.GET)
	@Secured(value = { "ROLE_Admin" })
	public @ResponseBody List<MedicalStaff> getRelations() {
		List<MedicalStaff> list = MedicalStaffService.medicalStaffList();
		List<MedicalStaff> copy = MedicalStaffService.medicalStaffList();
		for( MedicalStaff ms : copy )
			if( ms.getPhysician_name().equals(""))
				list.remove(ms);
		return list;
	}
	
	@RequestMapping(value = "/relation/getRelation_mspById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_Admin" })
	public @ResponseBody MedicalStaff findMSById(
			@RequestParam (value="relation_id", required = true) int relation_id ) {
		return MedicalStaffService.getMSByID(relation_id);
	}
	
	@RequestMapping(value = "/relation/updateMSPRelation", method = RequestMethod.GET)
	@Secured(value = { "ROLE_Admin" })
	public @ResponseBody String updateRelation(
			@RequestParam (value="relation_id", required = true) int relation_id,
			@RequestParam (value="physician_name", required = true ) String physician_name,
			@RequestParam (value="medical_staff_name", required = true ) String ms_name ) {
		MedicalStaff ms = MedicalStaffService.getMSByID( relation_id );
		ms.setMs_name(ms_name);
		ms.setPhysician_name(physician_name);
		//!!!!!!!!!!physician ID
		boolean result =  MedicalStaffService.updateMS(ms);
		if( result )
			return "s";
		else 
			return "d";
	}
	
	@RequestMapping(value = "/relation/deleteMSPRelation", method = RequestMethod.GET)
	@Secured(value = { "ROLE_Admin" })
	public @ResponseBody String deleteMSPRelation(
			@RequestParam(value="relation_id", required = true ) int relation_id ) {
		 MedicalStaff ms = MedicalStaffService.getMSByID( relation_id );
		boolean result = MedicalStaffService.deleteMS(ms);
		if( result )
			return "s";
		else 
			return "d";
	}
	
// add	
//	@RequestMapping(value = "/relation/deleteMSPRelation", method = RequestMethod.GET)
//	@Secured(value = { "ROLE_Admin" })
//	public @ResponseBody String deleteMSPRelation(
//			@RequestParam(value="relation_msp_id", required = true ) int relation_id ) {
//		 MedicalStaff ms = MedicalStaffService.getMSByID( relation_id );
//		boolean result = MedicalStaffService.deleteMS(ms);
//		if( result )
//			return "s";
//		else 
//			return "d";
//	}
	
}

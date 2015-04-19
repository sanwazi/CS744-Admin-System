package com.example.EMR_Admin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.admin.service.AdminService;
import com.example.EMR_Admin.authentication.data.Admin;
import com.example.EMR_Admin.authentication.service.CustomUserDetailsService;
import com.example.EMR_Admin.department.service.DepartmentService;
import com.example.EMR_Admin.diagnostic_test.service.DiagnosticTestServcie;
import com.example.EMR_Admin.drug.service.DrugService;
import com.example.EMR_Admin.medical_staff.service.MedicalStaffService;
import com.example.EMR_Admin.patient.service.PatientService;
import com.example.EMR_Admin.physician.service.PhysicianService;
import com.example.EMR_Admin.surgery.service.SurgeryService;
import com.example.EMR_Admin.treatment.service.TreatmentService;

@Controller
public class AdminController {

	
	@Autowired
	AdminService adminService;
	
	@Autowired 
	PhysicianService phService;
	
	@Autowired 
	PatientService paService;
	
	@Autowired
	MedicalStaffService msService;
	
	@Autowired 
	DepartmentService deService;
	
	@Autowired
	DrugService drugService;
	
	@Autowired
	TreatmentService treatmentService;
	
	@Autowired
	DiagnosticTestServcie diService;
	
	@Autowired
	SurgeryService surgeryService;

	@RequestMapping(value = "/adminList", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Admin> getAdminList() {
		List<Admin> list = adminService.getAdminList();
		return list;
	}
	
	@RequestMapping(value = "/admin/getAdminById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody Admin getAdminById( 
			@RequestParam( value="admin_id", required = true) int admin_id ) {
		List<Admin> list = adminService.getAdminList();
		for( Admin admin : list )
			if( admin.getAdminId() == admin_id )
				return admin;
		return null;
	}

	@RequestMapping(value = "/admin/addAdmin", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addAdmin(
			@RequestParam(value = "account", required = true) String admin_account,
			@RequestParam(value = "password", required = true) String admin_password) {
		Admin admin = new Admin();
		admin.setAdminAccount(admin_account);
		admin.setAdminPassword(admin_password);
		boolean result = adminService.addAdmin(admin);
		if (result)
			return "s";
		else
			return "d";
	}

	@RequestMapping(value = "/admin/deleteAdmin", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String deleteAdmin(
			@RequestParam(value = "admin_id", required = true) int admin_id) {
		Admin admin = adminService.findAdminById(admin_id);
		System.out.println("account: " + admin.getAdminAccount() + "password: "+ admin.getAdminPassword());
		boolean result = adminService.deleteAdmin(admin);

		if (result)
			return "s";
		else
			return "d";
	}
	
	@RequestMapping(value = "/admin/updateAdmin", method = RequestMethod.GET )
	@Secured (value = "ROLE_ADMIN" )
	public @ResponseBody String updateAdmin( @RequestParam (value="adminId",required = true ) int admin_id,
			@RequestParam (value="adminAccount", required = true ) String admin_account,
			@RequestParam (value = "adminPassword", required = true) String admin_password){
		Admin admin = new Admin();
		admin.setAdminId(admin_id);
		admin.setAdminAccount( admin_account);
		admin.setAdminPassword(admin_password);
		boolean result = adminService.updateAdmin(admin);
		if(result)
			return "s";
		else
			return "d";
	}
	
	@RequestMapping(value="/admin/currentAdmin", method = RequestMethod.GET)
	@Secured(value={"ROLE_ADMIN"})
	public @ResponseBody UserDetails getCurrentAdmin(){
		return CustomUserDetailsService.currentUserDetails();
	}
	
	@RequestMapping(value = "/loadStatistics", method = RequestMethod.GET)
	@Secured(value={"ROLE_ADMIN"})
	public @ResponseBody int[] loadStatistics() {
		int[] array = new int[9];
		array[0] = adminService.getAdminList().size();
		array[1] = phService.getPhysicianList().size();
		array[2] = paService.patientList().size();
		array[3] = msService.medicalStaffList().size();
		array[4] = deService.getAll().size();
		array[5] = drugService.getAll().size();
		array[6] = surgeryService.getAll().size();
		array[7] = treatmentService.findAll().size();
		array[8] = diService.findAll().size();
		return array;
	}
}

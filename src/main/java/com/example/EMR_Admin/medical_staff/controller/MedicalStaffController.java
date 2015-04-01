package com.example.EMR_Admin.medical_staff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.medical_staff.data.MedicalStaff;
import com.example.EMR_Admin.medical_staff.service.MedicalStaffService;

@Controller
public class MedicalStaffController {

	@Autowired
	MedicalStaffService MedicalStaffService;

	@RequestMapping(value = "/medicalstaff/list", method = RequestMethod.GET)
	@Secured(value = { "ROLE_Admin" })
	public @ResponseBody List<MedicalStaff> getMedicalStaffList() {
		List<MedicalStaff> list = MedicalStaffService.medicalStaffList();
		return list;
	}
}

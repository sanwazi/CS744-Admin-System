package com.example.EMR_Admin.physician.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.authentication.data.Admin;
import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.physician.service.PhysicianService;

@Controller
public class PhysicianController {

	@Autowired
	private PhysicianService pService;

	@RequestMapping(value = "/physician/autocomplete", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Physician> searchWithInput(
			@RequestParam(value = "input", required = true) String input) {
		List<Physician> list = pService.searchWithInput(input);
		return list;
	}
	
	@RequestMapping(value = "/physicianList", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Physician> getAdminList(
			) {
		List<Physician> list = pService.getPhysicianList();
		return list;
	}
}

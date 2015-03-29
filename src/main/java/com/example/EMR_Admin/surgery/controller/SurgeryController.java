package com.example.EMR_Admin.surgery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.surgery.data.Surgery;
import com.example.EMR_Admin.surgery.service.SurgeryService;

@Controller
public class SurgeryController {
	@Autowired
	SurgeryService sService;
	
	@RequestMapping(value = "/surgery/autocomplete", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Surgery> searchWithInput(
			@RequestParam(value = "input", required = true) String input) {
		List<Surgery> list =sService.searchWithInput(input);
		return list;
	}
	
	@RequestMapping(value = "/surgery/getSurgery", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Surgery> getAll() {
		List<Surgery> list =sService.getAll();
		return list;
	}

}

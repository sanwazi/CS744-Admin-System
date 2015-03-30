package com.example.EMR_Admin.drug.controller;

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

import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.drug.service.DrugService;
import com.example.EMR_Admin.emr.data.Emr;

@Controller
public class DrugController {

	@Autowired
	DrugService drugService;
	
	@RequestMapping(value = "/drug/searchWithInput", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Drug> searchWithInput(
			@RequestParam(value = "drugInput", required = true) String input) {
		List<Drug> list = drugService.searchWithInput(input);
		return list;
	}
	
	@RequestMapping(value = "/drug/getDrug", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Drug> getAll() {
		List<Drug> list = drugService.getAll();
		return list;
	}
	
	@RequestMapping(value = "/drug/addDrug", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addDrug(
			@RequestParam(value = "drugName", required = true) String drug_name
			) {
		Drug drug = new Drug();
		drug.setDrug_name(drug_name);
		boolean result = drugService.addDrug(drug);
		if(result){
			return "s";
		}else return "d";
	}
	
	
}

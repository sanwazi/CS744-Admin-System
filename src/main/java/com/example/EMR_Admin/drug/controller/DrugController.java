package com.example.EMR_Admin.drug.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.drug.service.DrugService;
import com.example.EMR_Admin.emr.data.Emr;
import com.example.EMR_Admin.patient.data.Patient;

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
			@RequestParam(value = "drug_name", required = true) String drug_name
			) {
		Drug drug = new Drug();
		drug.setDrug_name(drug_name);
		boolean result = drugService.addDrug(drug);
		if(result){
			return "s";
		}else return "d";
	}
	
	@RequestMapping(value = "/drug/getDrugById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody Drug searchWithInput(
			@RequestParam(value = "drug_id", required = true) int drug_id) {
		List<Drug> list = drugService.getDrugById(drug_id);
		return list.get(0);
	}
	
	@RequestMapping(value = "/drug/updateDrug", method = RequestMethod.GET)
	@Secured(value = {"ROLE_ADMIN"})
	public @ResponseBody String updateDrug(
			@RequestParam(value = "drug_id", required=true) int drug_id,
			@RequestParam(value = "drug_name") String drug_name){
		Drug drug = new Drug();
		drug.setDrug_id(drug_id);
		drug.setDrug_name(drug_name);
		boolean result = drugService.updateDrug(drug);
		if(result) return "s";
		else return "d";
	}
	
	@RequestMapping(value = "/drug/deleteDrug", method = RequestMethod.GET)
	@Secured(value = {"ROLE_ADMIN"})
	public @ResponseBody String deleteDrug(
			@RequestParam(value = "drug_id", required=true) int drug_id){
		Drug drug = new Drug();
		drug.setDrug_id(drug_id);
		boolean result = drugService.deleteDrug(drug);
		if(result) return "s";
		else return "d";
	}
	
	@RequestMapping(value="/drug/addAllDrugFromPharmacyToDb", method = RequestMethod.POST)
	@Secured(value = {"ROLE_ADMIN"})
	public @ResponseBody String addAllDrugFromPharmacyToDb(
			@RequestBody List<Drug> jsonList){
		for(Drug d : jsonList){
			System.out.println("Controller just get"+d.getDrug_id()+"  "+d.getDrug_name());
		}
		boolean result = drugService.addDrugList(jsonList);
		for(Drug d : jsonList){
			System.out.println("Controller after save"+d.getDrug_id()+"  "+d.getDrug_name());
		}
		return jsonList.toString();
//		if(result) return "s";
//		else return "d";
	}
	
}

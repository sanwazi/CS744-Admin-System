package com.example.EMR_Admin.surgery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.drug.data.Drug;
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
		List<Surgery> list = sService.searchWithInput(input);
		return list;
	}

	@RequestMapping(value = "/surgery/getSurgery", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Surgery> getAll() {
		List<Surgery> list = sService.getAll();
		return list;
	}

	@RequestMapping(value = "/surgery/addSurgery", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addSurgery(
			@RequestParam(value = "surgery_name") String surgery_name,
			@RequestParam(value = "cost") double cost) {
		Surgery surgery = new Surgery();
		surgery.setSurgery_name(surgery_name);
		surgery.setCost(cost);
		boolean result = sService.addSurgery(surgery);
		if (result) {
			return "s";
		} else
			return "d";
	}

	@RequestMapping(value = "/surgery/getSurgeryById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody Surgery getSurgeryById(
			@RequestParam(value = "surgery_id", required = true) int surgery_id) {
		List<Surgery> list = sService.getById(surgery_id);
		return list.get(0);
	}

	@RequestMapping(value = "/surgery/updateSurgery", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String updateSurgery(
			@RequestParam(value = "surgery_id", required = true) int surgery_id,
			@RequestParam(value = "surgery_name") String surgery_name,
			@RequestParam(value = "cost") double cost) {
		Surgery surgery = new Surgery();
		surgery.setSurgery_id(surgery_id);
		surgery.setSurgery_name(surgery_name);
		surgery.setCost(cost);
		boolean result = sService.updateSurgery(surgery);
		if (result)
			return "s";
		else
			return "d";
	}

	@RequestMapping(value = "/surgery/deleteSurgery", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String deleteSurgery(
			@RequestParam(value = "surgery_id", required = true) int surgery_id) {
		Surgery surgery = new Surgery();
		surgery.setSurgery_id(surgery_id);
		boolean result = sService.deleteSurgery(surgery);
		if (result)
			return "s";
		else
			return "d";
	}

}

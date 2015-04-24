package com.example.EMR_Admin.treatment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.treatment.data.Treatment;
import com.example.EMR_Admin.treatment.service.TreatmentService;

@Controller
public class TreatmentController {

	@Autowired
	TreatmentService tService;

	@RequestMapping(value = "/treatment/treatmentList", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Treatment> findAll() {
		List<Treatment> list = tService.findAll();
		return list;
	}

	@RequestMapping(value = "/treatment/getTreatment", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody Treatment getTreatmentById(
			@RequestParam(value = "treatment_id", required = true) int id) {
		return tService.findTreatmentById(id);
	}

	@RequestMapping(value = "/treatment/updateTreatment", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String updateTreatment(
			@RequestParam(value = "treatment_id", required = true) int treatment_id,
			@RequestParam(value = "treatment_name") String treatment_name,
			@RequestParam(value = "cost") double cost,
			@RequestParam(value = "can_ms_do") String canDo) {
		Treatment treatment = new Treatment();
		treatment.setTreatment_id(treatment_id);
		treatment.setTreatment_name(treatment_name);
		treatment.setTreatment_price(cost);
		treatment.setCan_medical_staff(canDo);
		boolean result = tService.updateTreatment(treatment);
		if (result)
			return "s";
		else
			return "d";
	}

	@RequestMapping(value = "/treatment/deleteTreatment", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String deleteTreatment(
			@RequestParam(value = "treatment_id", required = true) int treatment_id) {
		Treatment Treatment = new Treatment();
		Treatment.setTreatment_id(treatment_id);
		boolean result = tService.deleteTreatment(Treatment);
		if (result)
			return "s";
		else
			return "d";
	}

	@RequestMapping(value = "/treatment/addTreatment", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addTreatment(
			@RequestParam(value = "treatment_name") String treatment_name,
			@RequestParam(value = "cost") double cost,
			@RequestParam(value = "can_ms_do") String can_ms_do) {
		Treatment Treatment = new Treatment();
		Treatment.setTreatment_name(treatment_name);
		Treatment.setTreatment_price(cost);
		Treatment.setCan_medical_staff(can_ms_do);
		boolean result = tService.addTreatment(Treatment);
		if (result) {
			return "s";
		} else
			return "d";
	}

}

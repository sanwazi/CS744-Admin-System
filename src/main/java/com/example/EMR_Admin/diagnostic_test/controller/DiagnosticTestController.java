package com.example.EMR_Admin.diagnostic_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.diagnostic_test.data.DiagnosticTest;
import com.example.EMR_Admin.diagnostic_test.service.DiagnosticTestServcie;

@Controller
public class DiagnosticTestController {
	@Autowired
	private DiagnosticTestServcie dtService;

	@RequestMapping(value = "/diagnostictest/autocomplete", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody List<DiagnosticTest> searchWithInput(
			@RequestParam(value = "input", required = true) String input) {
		List<DiagnosticTest> list = dtService.searchWithInput(input);
		return list;
	}

	@RequestMapping(value = "/diagnosticList", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<DiagnosticTest> findAll() {
		List<DiagnosticTest> list = dtService.findAll();
		return list;
	}

	@RequestMapping(value = "/diagnostic/addDiagnostic", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addDiagnostic(
			@RequestParam(value = "diagnosticName", required = true) String dt_name,
			@RequestParam(value = "diagnosticCost", required = true) String dt_cost) {
		DiagnosticTest dt = new DiagnosticTest();
		dt.setDiagnostic_test_name(dt_name);
		dt.setCost(Integer.parseInt(dt_cost));
		boolean result = dtService.addDiagnostic(dt);
		if (result) {
			return "s";
		} else
			return "d";
	
	}

	@RequestMapping(value = "/diagnostic/deleteDiagnostic", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<DiagnosticTest> deleteDiagnostic(
			@RequestParam(value = "diagnosticId", required = true) String dt_id) {
		DiagnosticTest dt = dtService.findDiagnosticById(Integer
				.parseInt(dt_id));
		boolean result = dtService.deleteDiagnostic(dt);
		
//		if (result)
//			return "s";
//		else
//			return "d";
		
		List<DiagnosticTest> list = dtService.findAll();
		return list;
	}

	@RequestMapping(value = "/diagnostic/updateDiagnostic", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String updateDiagnostic(
			@RequestParam(value = "diagnostic_id", required = true) int diagnostic_test_id,
			@RequestParam(value = "diagnostic_name") String diagnostic_test_name,
			@RequestParam(value = "cost") int cost) {
		DiagnosticTest diagnosticTest = new DiagnosticTest();
		diagnosticTest.setDiagnostic_test_id(diagnostic_test_id);
		diagnosticTest.setDiagnostic_test_name(diagnostic_test_name);
		diagnosticTest.setCost(cost);
		boolean result = dtService.updateDiagnostic(diagnosticTest);
		if (result)
			return "s";
		else
			return "d";
	}
	
	@RequestMapping(value = "/diagnostic/getdiagnosticById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody DiagnosticTest getdiagnosticById(
			@RequestParam(value = "diagnostic_id", required = true) int diagnostic_test_id) {
		DiagnosticTest diagnosticTest = dtService.findDiagnosticById(diagnostic_test_id);;
		return diagnosticTest;
	}

}

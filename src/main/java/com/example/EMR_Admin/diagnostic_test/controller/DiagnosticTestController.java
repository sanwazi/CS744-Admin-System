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
}

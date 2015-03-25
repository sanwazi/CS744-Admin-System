package com.relation_transcription_diagnostic_test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.relation_transcription_diagnostic_test.data.TranscriptionDiagnosticRelation;
import com.relation_transcription_diagnostic_test.service.TranscriptionDiagnosticRelationService;

@Controller
public class TranscriptionDiagnosticRelationController {

	@Autowired
	TranscriptionDiagnosticRelationService tdrService;

	@RequestMapping(value = "/relation_transcription_diagnostic_test/getDiagnostic", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody List<TranscriptionDiagnosticRelation> getDiagnosticListByTranscriptionId(
			@RequestParam(value = "transcription_id", required = true) int transcriptionId) {
		return tdrService.getByTranscriptionId(transcriptionId);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody void upload() {

	}

	@RequestMapping(value = "/diagnostictest/add", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody String add(
			@RequestParam(value = "transcriptionId", required = true) int transcriptionId,
			@RequestParam(value = "testName", required = true) String testName,
			@RequestParam(value = "testId", required = true) int testId) {
		TranscriptionDiagnosticRelation tdr = new TranscriptionDiagnosticRelation();
		tdr.setDiagnostic_test_id(testId);
		tdr.setDiagnostic_test_name(testName);
		tdr.setTranscription_id(transcriptionId);
		tdrService.add(tdr);
		return "success";
	}
}

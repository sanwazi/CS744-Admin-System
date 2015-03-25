package com.transcription.controller;

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

import com.transcription.data.Transcription;
import com.transcription.service.TranscriptionService;

@Controller
public class TranscriptionController {

	@Autowired
	private TranscriptionService transcriptionService;

	@RequestMapping(value = "/emr/getTranscription", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody List<Transcription> getTranscriptionList(
			@RequestParam(value = "emr_id", required = true) int emrId) {
		List<Transcription> list = transcriptionService
				.getTranscriptionsByEmrId(emrId);
		return list;
	}

	@RequestMapping(value = "/transcription/getTranscription", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody Transcription getTranscriptionById(
			@RequestParam(value = "transcription_id", required = true) int transcription_id) {
		Transcription transcription = transcriptionService
				.getTranscriptionById(transcription_id);
		return transcription;
	}

	@RequestMapping(value = "/transcription/create", method = RequestMethod.GET)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody String create(
			@RequestParam(value = "emrId", required = true) int emrId,
			@RequestParam(value = "patientId", required = true) int patientId) {
		Integer transcriptionId = transcriptionService.create(emrId, patientId);
		return transcriptionId + "";
	}

	@RequestMapping(value = "/transcription/update", method = RequestMethod.POST)
	@Secured(value = { "ROLE_PHYSICIAN" })
	public @ResponseBody String update(
			@RequestParam(value = "transcriptionId", required = true) int transcriptionId,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "abstraction", required = true) String abstraction) {

		transcriptionService.update(transcriptionId, content, abstraction);
		return "success";
	}
}

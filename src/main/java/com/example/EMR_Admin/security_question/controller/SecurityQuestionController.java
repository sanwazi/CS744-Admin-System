package com.example.EMR_Admin.security_question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.security_question.data.SecurityQuestion;
import com.example.EMR_Admin.security_question.service.SecurityQuestionService;

@Controller
public class SecurityQuestionController {
	@Autowired
	SecurityQuestionService sqService;
	
	@RequestMapping(value = "/security_question/getAll", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<SecurityQuestion> getAll() {
		List<SecurityQuestion> list =sqService.getAll();
		return list;
	}
}

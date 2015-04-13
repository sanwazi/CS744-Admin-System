package com.example.EMR_Admin.security_question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.authentication.service.CustomUserDetailsService;
import com.example.EMR_Admin.security_question.data.SecurityQuestion;
import com.example.EMR_Admin.security_question.service.SecurityQuestionService;

@Controller
public class SecurityQuestionController {
	@Autowired
	SecurityQuestionService sqService;
	@Autowired
	CustomUserDetailsService cudService;
	
	@RequestMapping(value = "/security_question/getAll", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<SecurityQuestion> getAll() {
		List<SecurityQuestion> list =sqService.getAll();
		return list;
	}
	
	@RequestMapping(value = "/security_question/getUserDetail", method = RequestMethod.GET)
	@Secured(value = {"ROLE_ADMIN"})
	public @ResponseBody UserDetails getUserDetail(){
		return CustomUserDetailsService.currentUserDetails();
	}
	
	@RequestMapping(value = "/security_question/getById", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<SecurityQuestion> getById(
			@RequestParam(value = "sq_id", required = true) int sq_id) {
		List<SecurityQuestion> list =sqService.getById(sq_id);
		return list;
	}
}

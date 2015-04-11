package com.example.EMR_Admin.security_question_answer_account_relation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.security_question_answer_account_relation.data.SecurityQuestionAnswerAccountRelation;
import com.example.EMR_Admin.security_question_answer_account_relation.service.SecurityQuestionAnswerAccountRelationService;

@Controller
public class SecurityQuestionAnswerAccountRelationController {
	@Autowired
	SecurityQuestionAnswerAccountRelationService sq_a_aService;
	
	@RequestMapping(value = "/sq_a_a/getAll", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<SecurityQuestionAnswerAccountRelation> getAll() {
		List<SecurityQuestionAnswerAccountRelation> list =sq_a_aService.getAll();
		return list;
	}
	
	@RequestMapping(value = "/sq_a_a/getByAdminAccount", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<SecurityQuestionAnswerAccountRelation> getByAdminAccount(
			@RequestParam(value = "admin_account", required = true) String admin_account
			) {
		List<SecurityQuestionAnswerAccountRelation> list =sq_a_aService.getByAdminAccount(admin_account);
		return list;
	}
	
}

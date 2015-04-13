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
	
	@RequestMapping(value = "/sq_a_a/addNew", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addNew(
			@RequestParam(value = "admin_account", required = true) String admin_account,
			@RequestParam(value = "sq_id1") int sq_id1,
			@RequestParam(value = "sq_id2") int sq_id2,
			@RequestParam(value = "sq_id3") int sq_id3,
			@RequestParam(value = "answer_1") String answer_1,
			@RequestParam(value = "answer_2") String answer_2,
			@RequestParam(value = "answer_3") String answer_3
			) {
		SecurityQuestionAnswerAccountRelation input1 = new SecurityQuestionAnswerAccountRelation(admin_account,sq_id1,answer_1);
		SecurityQuestionAnswerAccountRelation input2 = new SecurityQuestionAnswerAccountRelation(admin_account,sq_id2,answer_2);
		SecurityQuestionAnswerAccountRelation input3 = new SecurityQuestionAnswerAccountRelation(admin_account,sq_id3,answer_3);
		return sq_a_aService.addNew(input1, input2, input3);
	}
	
}

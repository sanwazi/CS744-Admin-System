package com.example.EMR_Admin.security_question_answer_account_relation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.security_question_answer_account_relation.dao.SecurityQuestionAnswerAccountRelationDao;
import com.example.EMR_Admin.security_question_answer_account_relation.data.SecurityQuestionAnswerAccountRelation;

@Service
public class SecurityQuestionAnswerAccountRelationService {
	@Autowired
	SecurityQuestionAnswerAccountRelationDao sq_a_aDao;
	
	public List<SecurityQuestionAnswerAccountRelation> getAll(){
		return sq_a_aDao.getAll();
	}
	
	public List<SecurityQuestionAnswerAccountRelation> getByAdminAccount(String admin_account){
		return sq_a_aDao.getByAdminAccount(admin_account);
	}
	
	public String addNew(SecurityQuestionAnswerAccountRelation input1, SecurityQuestionAnswerAccountRelation input2, SecurityQuestionAnswerAccountRelation input3){
		return sq_a_aDao.addNew(input1, input2, input3);
	}
}

package com.example.EMR_Admin.security_question_answer_account_relation.service;

import java.util.List;
import java.util.Random;

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
	
	public SecurityQuestionAnswerAccountRelation getRandSQByAdminAccount(String admin_account){
		List<SecurityQuestionAnswerAccountRelation> sqs = getByAdminAccount(admin_account);
		if(sqs.isEmpty()){
			return null;
		}else{
			int randomNumber = randInt(0,sqs.size()-1);
			return sqs.get(randomNumber);
		}
	}
	
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}

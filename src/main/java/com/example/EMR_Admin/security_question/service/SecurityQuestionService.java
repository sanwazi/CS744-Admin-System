package com.example.EMR_Admin.security_question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.security_question.dao.SecurityQuestionDao;
import com.example.EMR_Admin.security_question.data.SecurityQuestion;

@Service
public class SecurityQuestionService {
	@Autowired
	SecurityQuestionDao sqDao;
	
	public List<SecurityQuestion> getAll(){
		return sqDao.getAll();
	}
}

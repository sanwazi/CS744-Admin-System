package com.example.EMR_Admin.security_question_answer_account_relation.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security_question_answer_account_relation")

public class SecurityQuestionAnswerAccountRelation {

	@Id
	@Column(name = "sq_relation_id")
	@GeneratedValue
	private int sq_relation_id;
	
	@Column(name = "sq_id")
	private int sq_id;
	
	@Column(name = "admin_account")
	private String admin_account;
	
	@Column(name = "answer")
	private String answer;
	
	public SecurityQuestionAnswerAccountRelation(String admin_account, int sq_id,
			String answer) {
		super();
		this.sq_id = sq_id;
		this.admin_account = admin_account;
		this.answer = answer;
	}


	public int getSq_relation_id() {
		return sq_relation_id;
	}

	public void setSq_relation_id(int sq_relation_id) {
		this.sq_relation_id = sq_relation_id;
	}

	public int getSq_id() {
		return sq_id;
	}

	public void setSq_id(int sq_id) {
		this.sq_id = sq_id;
	}

	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}

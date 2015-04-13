package com.example.EMR_Admin.security_question.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security_question")
public class SecurityQuestion {
	@Id
	@Column(name = "sq_id")
	@GeneratedValue
	private int sq_id;

	@Column(name = "sq_name")
	private String sq_name;
	
	@Column(name = "sq_step")
	private int sq_step;

	public int getSq_id() {
		return sq_id;
	}

	public void setSq_id(int sq_id) {
		this.sq_id = sq_id;
	}

	public String getSq_name() {
		return sq_name;
	}

	public void setSq_name(String sq_name) {
		this.sq_name = sq_name;
	}

	public int getSq_step() {
		return sq_step;
	}

	public void setSq_step(int sq_step) {
		this.sq_step = sq_step;
	}

	

}

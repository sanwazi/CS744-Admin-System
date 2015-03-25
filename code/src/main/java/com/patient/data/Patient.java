package com.patient.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Han Chen
 *
 * Feb 26, 2015
 */
@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@Column(name = "patient_id")
	@GeneratedValue
	private int patient_id;

	@Column(name = "patient_name")
	private String patient_name;
	@Column(name = "patient_gender")
	private String patient_gender;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "patient_birthday")
	private Date patient_birthday;
	
	/**
	 * @return the patient_id
	 */
	public int getPatient_id() {
		return patient_id;
	}
	/**
	 * @param patient_id the patient_id to set
	 */
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	/**
	 * @return the patient_name
	 */
	public String getPatient_name() {
		return patient_name;
	}
	/**
	 * @param patient_name the patient_name to set
	 */
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	/**
	 * @return the patient_gender
	 */
	public String getPatient_gender() {
		return patient_gender;
	}
	/**
	 * @param patient_gender the patient_gender to set
	 */
	public void setPatient_gender(String patient_gender) {
		this.patient_gender = patient_gender;
	}
	/**
	 * @return the patient_birthday
	 */
	public Date getPatient_birthday() {
		return patient_birthday;
	}
	/**
	 * @param patient_birthday the patient_birthday to set
	 */
	public void setPatient_birthday(Date patient_birthday) {
		this.patient_birthday = patient_birthday;
	}
	
	
	

}
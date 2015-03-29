package com.example.EMR_Admin.patient_physician_relation.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "relation_physician_patient")
public class RelationPhysicianPatient {
	@Id
	@Column(name = "relation_id")
	@GeneratedValue
	private int relation_id;

	@Column(name = "physician_id")
	private int physician_id;

	@Column(name = "physician_name")
	private String physician_name;

	@Column(name = "patient_id")
	private int patient_id;

	@Column(name = "patient_name")
	private String patient_name;

	@Column(name = "patient_gender")
	private String patient_gender;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "patient_birthday")
	private Date patient_birthday;

	@Column(name = "relation_type")
	private String relation_type;

	@Column(name = "primary_physician_id")
	private int primary_physician_id;

	@Column(name = "primary_physician_name")
	private String primary_physician_name;

	@Column(name = "access_right")
	private String access_right;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "start_date")
	private Date start_date;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "end_date")
	private Date end_date;

	public int getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}

	public int getPhysician_id() {
		return physician_id;
	}

	public void setPhysician_id(int physician_id) {
		this.physician_id = physician_id;
	}

	public String getPhysician_name() {
		return physician_name;
	}

	public void setPhysician_name(String physician_name) {
		this.physician_name = physician_name;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getPatient_gender() {
		return patient_gender;
	}

	public void setPatient_gender(String patient_gender) {
		this.patient_gender = patient_gender;
	}

	public Date getPatient_birthday() {
		return patient_birthday;
	}

	public void setPatient_birthday(Date patient_birthday) {
		this.patient_birthday = patient_birthday;
	}

	public String getRelation_type() {
		return relation_type;
	}

	public void setRelation_type(String relation_type) {
		this.relation_type = relation_type;
	}

	public int getPrimary_physician_id() {
		return primary_physician_id;
	}

	public void setPrimary_physician_id(int primary_physician_id) {
		this.primary_physician_id = primary_physician_id;
	}

	public String getPrimary_physician_name() {
		return primary_physician_name;
	}

	public void setPrimary_physician_name(String primary_physician_name) {
		this.primary_physician_name = primary_physician_name;
	}

	public String getAccess_right() {
		return access_right;
	}

	public void setAccess_right(String access_right) {
		this.access_right = access_right;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

}

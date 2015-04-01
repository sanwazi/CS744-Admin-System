package com.example.EMR_Admin.medical_staff.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medical_staff")
public class MedicalStaff {

	@Id
	@Column(name = "msid")
	@GeneratedValue
	private String ms_id;
	
	@Column(name = "ms_name")
	private String ms_name;

	@Column(name = "ms_account")
	private String ms_account;

	@Column(name = "password")
	private String password;
	
	@Column(name = "physician_id")
	private String physician_id;
	
	@Column(name = "physician_name")
	private String physician_name;

	public String getMs_id() {
		return ms_id;
	}

	public void setMs_id(String ms_id) {
		this.ms_id = ms_id;
	}

	public String getMs_name() {
		return ms_name;
	}

	public void setMs_name(String ms_name) {
		this.ms_name = ms_name;
	}

	public String getMs_account() {
		return ms_account;
	}

	public void setMs_account(String ms_account) {
		this.ms_account = ms_account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhysician_id() {
		return physician_id;
	}

	public void setPhysician_id(String physician_id) {
		this.physician_id = physician_id;
	}

	public String getPhysician_name() {
		return physician_name;
	}

	public void setPhysician_name(String physician_name) {
		this.physician_name = physician_name;
	}
}

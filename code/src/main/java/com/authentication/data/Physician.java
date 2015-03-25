package com.authentication.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "physician")
public class Physician {
	@Id
	@Column(name = "physician_id")
	@GeneratedValue
	private int physicianId;

	@Column(name = "physician_name")
	private String physicianName;

	@Column(name = "physician_gender")
	private String physicianGender;

	@Column(name = "physician_birthday")
	private Date physicianBirthday;

	@Column(name = "account")
	private String account;

	@Column(name = "password")
	private String password;

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getPhysicianGender() {
		return physicianGender;
	}

	public void setPhysicianGender(String physicianGender) {
		this.physicianGender = physicianGender;
	}

	public Date getPhysicianBirthday() {
		return physicianBirthday;
	}

	public void setPhysicianBirthday(Date physicianBirthday) {
		this.physicianBirthday = physicianBirthday;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
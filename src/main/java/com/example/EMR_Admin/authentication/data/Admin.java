package com.example.EMR_Admin.authentication.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@Column(name = "admin_id")
	@GeneratedValue
	private String adminId;

	@Column(name = "admin_account")
	private String adminAccount;

	@Column(name = "admin_password")
	private String adminPassword;

	/**
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the adminAccount
	 */
	public String getAdminAccount() {
		return adminAccount;
	}

	/**
	 * @param adminAccount the adminAccount to set
	 */
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	/**
	 * @return the adminPassword
	 */
	public String getAdminPassword() {
		return adminPassword;
	}

	/**
	 * @param adminPassword the adminPassword to set
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	
}

package com.example.EMR_Admin.log.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="log_admin")
public class Log {

	@Id
	@Column(name = "log_id")
	@GeneratedValue
	private int log_id;
	
	@Column(name = "admin_name")
	private String admin_name;

	@Column(name = "table")
	private String table;
	
	@Column(name = "action")
	private String action;

	@Column(name = "date")
	private Date date;
	
	

	

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

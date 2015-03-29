package com.example.EMR_Admin.relation_physician_department.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relation_physician_department")
public class DepartmentPhysicianRelation {
	@Id
	@Column(name = "relation_id")
	@GeneratedValue
	private int relation_id;
	
	@Column(name = "physician_id")
	private int physician_id;
	
	@Column(name = "physician_name")
	private String physician_name;
	
	@Column(name = "department_id")
	private int department_id;
	
	@Column(name = "department_name")
	private String department_name;

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

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	
	
}

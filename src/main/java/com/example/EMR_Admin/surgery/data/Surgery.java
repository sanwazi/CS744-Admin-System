package com.example.EMR_Admin.surgery.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "surgery")
public class Surgery {
	@Id
	@Column(name = "surgery_id")
	@GeneratedValue
	private int surgery_id;

	@Column(name = "surgery_name")
	private String surgery_name;

	@Column(name = "cost")
	private double cost;

	public int getSurgery_id() {
		return surgery_id;
	}

	public void setSurgery_id(int surgery_id) {
		this.surgery_id = surgery_id;
	}

	public String getSurgery_name() {
		return surgery_name;
	}

	public void setSurgery_name(String surgery_name) {
		this.surgery_name = surgery_name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}

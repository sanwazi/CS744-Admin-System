package com.example.EMR_Admin.treatment.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "treatment")
public class Treatment {

	@Id
	@Column(name = "treatment_id")
	@GeneratedValue
	private int treatment_id;

	@Column(name = "treatment_name")
	private String treatment_name;

	@Column(name = "treatment_price")
	private double treatment_price;

	@Column(name = "can_medical_staff")
	private String can_medical_staff;

	public int getTreatment_id() {
		return treatment_id;
	}

	public void setTreatment_id(int treatment_id) {
		this.treatment_id = treatment_id;
	}

	public String getTreatment_name() {
		return treatment_name;
	}

	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
	}

	public double getTreatment_price() {
		return treatment_price;
	}

	public void setTreatment_price(double treatment_price) {
		this.treatment_price = treatment_price;
	}

	public String getCan_medical_staff() {
		return can_medical_staff;
	}

	public void setCan_medical_staff(String can_medical_staff) {
		this.can_medical_staff = can_medical_staff;
	}

	public String toString() {
		return "{\"treatment_id\":\"" + treatment_id
				+ "\", \"treatment_name\":\"" + treatment_name
				+ "\",\"treatment_price\":\"" + treatment_price
				+ "\",\"can_medical_staff\":\"" + can_medical_staff + "\"}";

	}
}

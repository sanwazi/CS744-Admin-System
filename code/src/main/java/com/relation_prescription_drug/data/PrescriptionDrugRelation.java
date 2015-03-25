package com.relation_prescription_drug.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relation_prescription_drug")
public class PrescriptionDrugRelation {
	@Id
	@Column(name = "rpd_id")
	@GeneratedValue
	private int rpd_id;

	@Column(name = "prescription_id")
	private int prescription_id;
	@Column(name = "drug_id")
	private int drug_id;
	@Column(name = "drug_name")
	private String drug_name;
	
	@Column(name = "amount")
	private String amount;
	
	public int getRpd_id() {
		return rpd_id;
	}

	public void setRpd_id(int rpd_id) {
		this.rpd_id = rpd_id;
	}

	public int getPrescription_id() {
		return prescription_id;
	}

	public void setPrescription_id(int prescription_id) {
		this.prescription_id = prescription_id;
	}

	public int getDrug_id() {
		return drug_id;
	}

	public void setDrug_id(int drug_id) {
		this.drug_id = drug_id;
	}

	public String getDrug_name() {
		return drug_name;
	}

	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}

package com.example.EMR_Admin.drug.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jingbo Chu	
 *
 * Mar 4, 2015
 */
@Entity
@Table(name = "drug_from_pharmacy")
public class Drug {

	@Id
	@Column(name = "drug_id")
	//@GeneratedValue
	private int drug_id;
	
	@Column(name="drug_unique_id")
	private String drug_unique_id;
	
	@Column(name="drug_lv")
	private String drug_lv;
	
	@Column(name="drug_name_medical")
	private String drug_name_medical;
	
	@Column(name="drug_name_commercial")
	private String drug_name_commercial;
	
	@Column(name="drug_unit")
	private String drug_unit;
	
	@Column(name="drug_dose")
	private String drug_dose;
	
	@Column(name="drug_reaction")
	private String drug_reaction;
	
	@Column(name="drug_usage")
	private String drug_usage;
	
	@Column(name="drug_status")
	private String drug_status;
	
	public Drug(){
		
	}

	public int getDrug_id() {
		return drug_id;
	}

	public void setDrug_id(int drug_id) {
		this.drug_id = drug_id;
	}

	public String getDrug_unique_id() {
		return drug_unique_id;
	}

	public void setDrug_unique_id(String drug_unique_id) {
		this.drug_unique_id = drug_unique_id;
	}

	public String getDrug_lv() {
		return drug_lv;
	}

	public void setDrug_lv(String drug_lv) {
		this.drug_lv = drug_lv;
	}

	public String getDrug_name_medical() {
		return drug_name_medical;
	}

	public void setDrug_name_medical(String drug_name_medical) {
		this.drug_name_medical = drug_name_medical;
	}

	public String getDrug_name_commercial() {
		return drug_name_commercial;
	}

	public void setDrug_name_commercial(String drug_name_commercial) {
		this.drug_name_commercial = drug_name_commercial;
	}

	public String getDrug_unit() {
		return drug_unit;
	}

	public void setDrug_unit(String drug_unit) {
		this.drug_unit = drug_unit;
	}

	public String getDrug_dose() {
		return drug_dose;
	}

	public void setDrug_dose(String drug_dose) {
		this.drug_dose = drug_dose;
	}

	public String getDrug_reaction() {
		return drug_reaction;
	}

	public void setDrug_reaction(String drug_reaction) {
		this.drug_reaction = drug_reaction;
	}

	public String getDrug_usage() {
		return drug_usage;
	}

	public void setDrug_usage(String drug_usage) {
		this.drug_usage = drug_usage;
	}

	public String getDrug_status() {
		return drug_status;
	}

	public void setDrug_status(String drug_status) {
		this.drug_status = drug_status;
	}

//	public Drug(String drug_name, String pharmacy_drug_id){
//		this.drug_name = drug_name;
//		this.pharmacy_drug_id = pharmacy_drug_id;
//	}
//	
//	/**
//	 * @param drug_id
//	 * @param drug_name
//	 * @param drug_term
//	 * @param drug_dispense
//	 */
//	public Drug(int drug_id, String drug_name) {
//		super();
//		this.drug_id = drug_id;
//		this.drug_name = drug_name;
//	}

    

}

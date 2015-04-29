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
	
	@Column(name = "drug_type")
	private String drug_type;
	
	@Column(name="drug_name_medical")
	private String drug_name_medical;
	
	@Column(name="drug_name_commercial")
	private String drug_name_commercial;
	
	@Column(name = "drug_price")
	private String drug_price;
	
	@Column(name="drug_lv")
	private String drug_lv;
	
//	@Column(name="drug_unit")
//	private String drug_unit;
	
	@Column(name="drug_dose")
	private String drug_dose;
	
	@Column(name = "drug_description")
	private String drug_description;
	
	@Column(name = "drug_common_uses")
	private String drug_common_uses;
	
	@Column(name="drug_usage")
	private String drug_usage;
	
	@Column(name="drug_cautions")
	private String drug_cautions;
	
	@Column(name="drug_side_effects")
	private String drug_side_effects;
	
	@Column(name="drug_status")
	private String drug_status;
	
	@Column(name="drug_refill_cycle")
	private String drug_refill_cycle;
	
	@Column(name="drug_refill_times")
	private String drug_refill_times;
	
	
	
	public String getDrug_refill_cycle() {
		return drug_refill_cycle;
	}



	public void setDrug_refill_cycle(String drug_refill_cycle) {
		this.drug_refill_cycle = drug_refill_cycle;
	}



	public String getDrug_refill_times() {
		return drug_refill_times;
	}



	public void setDrug_refill_times(String drug_refill_times) {
		this.drug_refill_times = drug_refill_times;
	}



	public Drug(){
		
	}
	
	
	
	public String getDrug_description() {
		return drug_description;
	}



	public void setDrug_description(String drug_description) {
		this.drug_description = drug_description;
	}



	public String getDrug_common_uses() {
		return drug_common_uses;
	}



	public void setDrug_common_uses(String drug_common_uses) {
		this.drug_common_uses = drug_common_uses;
	}



	public String getDrug_cautions() {
		return drug_cautions;
	}



	public void setDrug_cautions(String drug_cautions) {
		this.drug_cautions = drug_cautions;
	}



	public String getDrug_side_effects() {
		return drug_side_effects;
	}



	public void setDrug_side_effects(String drug_side_effects) {
		this.drug_side_effects = drug_side_effects;
	}



	/**
	 * @return the drug_type
	 */
	public String getDrug_type() {
		return drug_type;
	}

	/**
	 * @param drug_type the drug_type to set
	 */
	public void setDrug_type(String drug_type) {
		this.drug_type = drug_type;
	}

	/**
	 * @return the drug_price
	 */
	public String getDrug_price() {
		return drug_price;
	}

	/**
	 * @param drug_price the drug_price to set
	 */
	public void setDrug_price(String drug_price) {
		this.drug_price = drug_price;
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

//	public String getDrug_unit() {
//		return drug_unit;
//	}
//
//	public void setDrug_unit(String drug_unit) {
//		this.drug_unit = drug_unit;
//	}

	public String getDrug_dose() {
		return drug_dose;
	}

	public void setDrug_dose(String drug_dose) {
		this.drug_dose = drug_dose;
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

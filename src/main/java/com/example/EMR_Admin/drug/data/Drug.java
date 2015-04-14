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
@Table(name = "drug")
public class Drug {

	@Id
	@Column(name = "drug_id")
	@GeneratedValue
	private int drug_id;
	
	@Column(name="drug_name")
	private String drug_name;
	
	@Column(name="pharmacy_drug_id")
	private String pharmacy_drug_id;
	
	public Drug(){
		
	}

	public Drug(String drug_name, String pharmacy_drug_id){
		this.drug_name = drug_name;
		this.pharmacy_drug_id = pharmacy_drug_id;
	}
	
	/**
	 * @param drug_id
	 * @param drug_name
	 * @param drug_term
	 * @param drug_dispense
	 */
	public Drug(int drug_id, String drug_name) {
		super();
		this.drug_id = drug_id;
		this.drug_name = drug_name;
	}

	
	
	public String getPharmacy_drug_id() {
		return pharmacy_drug_id;
	}

	public void setPharmacy_drug_id(String pharmacy_drug_id) {
		this.pharmacy_drug_id = pharmacy_drug_id;
	}

	public String getDrug_name() {
		return drug_name;
	}


	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}


	/**
	 * @return the drug_id
	 */
	public int getDrug_id() {
		return drug_id;
	}

	/**
	 * @param drug_id the drug_id to set
	 */
	public void setDrug_id(int drug_id) {
		this.drug_id = drug_id;
	}


}

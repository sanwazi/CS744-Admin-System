package com.example.EMR_Admin.emr.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "emr")
public class Emr {
	@Id
	@Column(name = "emr_id")
	@GeneratedValue
	private int emr_id;

	@Column(name = "patient_id")
	private int patient_id;

	@Column(name = "patient_name")
	private String patient_name;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "race")
	private String race;
	
	@Column(name = "occupation")
	private String occupation;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "height")
	private String height;
	
	@Column(name = "weight")
	private String weight;
	
	@Column(name = "blood_group")
	private String blood_group;
	
	@Column(name = "vaccinations")
	private String vaccinations;
	
	@Column(name = "diabetes")
	private String diabetes;
	
	@Column(name = "allergies")
	private String allergies;
	
	
	/**
	 * @param emr_id
	 * @param patient_id
	 * @param patient_name
	 * @param birthday
	 * @param race
	 * @param occupation
	 * @param address
	 * @param height
	 * @param weight
	 * @param blood_group
	 * @param vaccinations
	 * @param diabetes
	 * @param allergies
	 */
	public Emr(int patient_id, String patient_name,  Date birthday,
			String race, String occupation, String address, String height,
			String weight, String blood_group, String vaccinations,
			String diabetes, String allergies) {
		super();
		//this.emr_id = emr_id;
		this.patient_id = patient_id;
		this.patient_name = patient_name;
		this.birthday = birthday;
		this.race = race;
		this.occupation = occupation;
		this.address = address;
		this.height = height;
		this.weight = weight;
		this.blood_group = blood_group;
		this.vaccinations = vaccinations;
		this.diabetes = diabetes;
		this.allergies = allergies;
	}
	
	public Emr(){}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the blood_group
	 */
	public String getBlood_group() {
		return blood_group;
	}

	/**
	 * @param blood_group the blood_group to set
	 */
	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	/**
	 * @return the vaccinations
	 */
	public String getVaccinations() {
		return vaccinations;
	}

	/**
	 * @param vaccinations the vaccinations to set
	 */
	public void setVaccinations(String vaccinations) {
		this.vaccinations = vaccinations;
	}

	/**
	 * @return the diabetes
	 */
	public String getDiabetes() {
		return diabetes;
	}

	/**
	 * @param diabetes the diabetes to set
	 */
	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}

	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	

	public int getEmr_id() {
		return emr_id;
	}

	public void setEmr_id(int emr_id) {
		this.emr_id = emr_id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

}
package com.transcription.data;

/**
 * @author Jingbo Chu	
 *
 * Mar 4, 2015
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "transcription")
public class Transcription {

	@Id
	@Column(name = "transcription_id")
	@GeneratedValue
	private int transcription_id;

	@Column(name = "emr_id")
	private int emr_id;

	@Column(name = "patient_id")
	private int patient_id;

	@Column(name = "patient_name")
	private String patient_name;

	@Column(name = "abstraction")
	private String abstraction;

	@Column(name = "content")
	private String content;

	@Column(name = "physician_id")
	private int physician_id;

	@Column(name = "physician_name")
	private String physician_name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "create_date")
	private Date create_date;

	public Transcription() {

	}

	/**
	 * @param transcription_id
	 * @param emr_id
	 * @param patient_id
	 * @param patient_name
	 * @param abstraction
	 * @param diagnostic_test
	 * @param surgery
	 * @param content
	 * @param physician_id
	 * @param physician_name
	 * @param create_date
	 */
	public Transcription(int transcription_id, int emr_id, int patient_id,
			String patient_name, String abstraction, String content,
			int physician_id, String physician_name, Date create_date) {
		super();
		this.transcription_id = transcription_id;
		this.emr_id = emr_id;
		this.patient_id = patient_id;
		this.patient_name = patient_name;
		this.abstraction = abstraction;

		this.content = content;
		this.physician_id = physician_id;
		this.physician_name = physician_name;
		this.create_date = create_date;
	}

	/**
	 * @return the abstraction
	 */
	public String getAbstraction() {
		return abstraction;
	}

	/**
	 * @param abstraction
	 *            the abstraction to set
	 */
	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the transcription_id
	 */
	public int getTranscription_id() {
		return transcription_id;
	}

	/**
	 * @param transcription_id
	 *            the transcription_id to set
	 */
	public void setTranscription_id(int transcription_id) {
		this.transcription_id = transcription_id;
	}

	/**
	 * @return the emr_id
	 */
	public int getEmr_id() {
		return emr_id;
	}

	/**
	 * @param emr_id
	 *            the emr_id to set
	 */
	public void setEmr_id(int emr_id) {
		this.emr_id = emr_id;
	}

	/**
	 * @return the patient_id
	 */
	public int getPatient_id() {
		return patient_id;
	}

	/**
	 * @param patient_id
	 *            the patient_id to set
	 */
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	/**
	 * @return the patient_name
	 */
	public String getPatient_name() {
		return patient_name;
	}

	/**
	 * @param patient_name
	 *            the patient_name to set
	 */
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	/**
	 * @return the physician_id
	 */
	public int getPhysician_id() {
		return physician_id;
	}

	/**
	 * @param physician_id
	 *            the physician_id to set
	 */
	public void setPhysician_id(int physician_id) {
		this.physician_id = physician_id;
	}

	/**
	 * @return the physician_name
	 */
	public String getPhysician_name() {
		return physician_name;
	}

	/**
	 * @param physician_name
	 *            the physician_name to set
	 */
	public void setPhysician_name(String physician_name) {
		this.physician_name = physician_name;
	}

	/**
	 * @return the create_date
	 */
	public Date getCreate_date() {
		return create_date;
	}

	/**
	 * @param create_date
	 *            the create_date to set
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

}

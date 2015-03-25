package com.relation_transcription_surgery.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "	relation_transcription_surgery")
public class TranscriptionSurgeryRelation {
	@Id
	@Column(name = "relation_id")
	@GeneratedValue
	private int relation_id;

	@Column(name = "transcription_id")
	private int transcription_id;
	@Column(name = "surgery_id")
	private int surgery_id;
	@Column(name = "surgery_name")
	private String surgery_name;

	public int getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}

	public int getTranscription_id() {
		return transcription_id;
	}

	public void setTranscription_id(int transcription_id) {
		this.transcription_id = transcription_id;
	}

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

}

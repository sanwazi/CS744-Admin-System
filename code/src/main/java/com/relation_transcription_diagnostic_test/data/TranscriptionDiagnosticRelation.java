package com.relation_transcription_diagnostic_test.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "relation_transcription_diagnostic_test")
public class TranscriptionDiagnosticRelation {
	@Id
	@Column(name = "relation_id")
	@GeneratedValue
	private int relation_id;

	@Column(name = "transcription_id")
	private int transcription_id;
	@Column(name = "diagnostic_test_id")
	private int diagnostic_test_id;
	@Column(name = "diagnostic_test_name")
	private String diagnostic_test_name;
	
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
	public int getDiagnostic_test_id() {
		return diagnostic_test_id;
	}
	public void setDiagnostic_test_id(int diagnostic_test_id) {
		this.diagnostic_test_id = diagnostic_test_id;
	}
	public String getDiagnostic_test_name() {
		return diagnostic_test_name;
	}
	public void setDiagnostic_test_name(String diagnostic_test_name) {
		this.diagnostic_test_name = diagnostic_test_name;
	}
	
}

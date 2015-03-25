package com.relation_transcription_diagnostic_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relation_transcription_diagnostic_test.dao.TranscriptionDiagnosticRelationDao;
import com.relation_transcription_diagnostic_test.data.TranscriptionDiagnosticRelation;

@Service
public class TranscriptionDiagnosticRelationService {
	
	@Autowired
	TranscriptionDiagnosticRelationDao tdrDao;

	public List<TranscriptionDiagnosticRelation> getByTranscriptionId(int transcriptionId) {
		// TODO Auto-generated method stub
		return tdrDao.getByTranscrptionId(transcriptionId);
	}

	public void add(TranscriptionDiagnosticRelation tdr) {
		// TODO Auto-generated method stub
		tdrDao.save(tdr);
	}
}

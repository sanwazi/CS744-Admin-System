package com.relation_transcription_surgery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relation_transcription_surgery.dao.TranscriptionSurgeryRelationDao;
import com.relation_transcription_surgery.data.TranscriptionSurgeryRelation;

@Service
public class TranscriptionSurgeryRelationService {
	
	@Autowired
	TranscriptionSurgeryRelationDao tsrDao;

	
	public List<TranscriptionSurgeryRelation> getByTranscriptionId(
			int transcriptionId) {
		// TODO Auto-generated method stub
		
		return tsrDao.getByTranscriptionId(transcriptionId);
	}


	public void add(TranscriptionSurgeryRelation tsr) {
		// TODO Auto-generated method stub
		tsrDao.add(tsr);
	}

}

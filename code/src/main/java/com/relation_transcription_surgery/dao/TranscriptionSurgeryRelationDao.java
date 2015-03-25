package com.relation_transcription_surgery.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.relation_transcription_diagnostic_test.data.TranscriptionDiagnosticRelation;
import com.relation_transcription_surgery.data.TranscriptionSurgeryRelation;

@Repository
public class TranscriptionSurgeryRelationDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<TranscriptionSurgeryRelation> getByTranscriptionId(
			int transcriptionId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from TranscriptionSurgeryRelation where transcription_id="
						+ transcriptionId);
		Transaction transaction = session.beginTransaction();
		List<TranscriptionSurgeryRelation> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public void add(TranscriptionSurgeryRelation tsr) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(tsr);
		session.getTransaction().commit();
		session.close();
	}

}

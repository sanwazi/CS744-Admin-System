package com.relation_transcription_diagnostic_test.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.patient_physician_relation.data.RelationPhysicianPatient;
import com.relation_transcription_diagnostic_test.data.TranscriptionDiagnosticRelation;

@Repository
public class TranscriptionDiagnosticRelationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<TranscriptionDiagnosticRelation> getByTranscrptionId(
			int transcriptionId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from TranscriptionDiagnosticRelation where transcription_id="
						+ transcriptionId);
		Transaction transaction = session.beginTransaction();
		List<TranscriptionDiagnosticRelation> list = q.list();
		transaction.commit();
		session.close();
		return list;

	}

	public void save(TranscriptionDiagnosticRelation tdr) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.save(tdr);
		session.getTransaction().commit();
		session.close();
	}

}

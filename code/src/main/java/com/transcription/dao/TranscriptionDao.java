package com.transcription.dao;

/**
 * @author Jingbo Chu	
 *
 * Mar 4, 2015
 */
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emr.data.Emr;
import com.transcription.data.Transcription;

@Repository
public class TranscriptionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Transcription> findTranscriptionsByEmrId(int emrId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Transcription where emr_id = "
				+ emrId + " ORDER BY transcription_id DESC");
		Transaction transaction = session.beginTransaction();
		List<Transcription> list = q.list();
		transaction.commit();
		session.close();
		return list;
	}

	public Transcription findTranscriptionById(int transcription_id) {
		Session session = sessionFactory.openSession();
		Query q = session
				.createQuery("from Transcription where transcription_id = "
						+ transcription_id);
		Transaction transaction = session.beginTransaction();
		List<Transcription> transcriptions = q.list();
		transaction.commit();
		session.close();
		Transcription transcription = (transcriptions.size() != 0 ? transcriptions
				.remove(0) : null);
		return transcription;
	}

	public Integer create(Transcription transcription) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		int transcriptionId = (Integer) session.save(transcription); // if you
																		// want
																		// to
																		// save
																		// the
																		// id to
																		// some
																		// variable
		session.getTransaction().commit();
		session.close();
		return transcriptionId;
	}

	public void update(int transcriptionId, String content, String abstraction) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Query query = session
				.createQuery("update Transcription set content = :content,abstraction =:abstraction "
						+ " where transcription_id = :transcriptionId");
		query.setParameter("abstraction", abstraction);
		query.setParameter("content", content);
		query.setParameter("transcriptionId", transcriptionId);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
}

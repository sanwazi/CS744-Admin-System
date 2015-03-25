package com.surgery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surgery.dao.SurgeryDao;
import com.surgery.data.Surgery;

@Service
public class SurgeryService {

	@Autowired
	SurgeryDao sDao;

	public List<Surgery> searchWithInput(String input) {
		// TODO Auto-generated method stub
		return sDao.serachWithInput(input);
	}
}

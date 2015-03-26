package com.example.EMR_Admin.diagnostic_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.diagnostic_test.dao.DiagnosticTestDao;
import com.example.EMR_Admin.diagnostic_test.data.DiagnosticTest;

@Service
public class DiagnosticTestServcie {

	@Autowired
	DiagnosticTestDao dtDao;

	public List<DiagnosticTest> searchWithInput(String input) {
		// TODO Auto-generated method stub
		List<DiagnosticTest> list = dtDao.serachWithInput(input);
		return list;
	}

}

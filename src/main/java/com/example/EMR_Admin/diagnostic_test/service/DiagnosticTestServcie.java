package com.example.EMR_Admin.diagnostic_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.diagnostic_test.dao.DiagnosticTestDao;
import com.example.EMR_Admin.diagnostic_test.data.DiagnosticTest;
import com.example.EMR_Admin.drug.data.Drug;

@Service
public class DiagnosticTestServcie {

	@Autowired
	DiagnosticTestDao dtDao;

	public List<DiagnosticTest> searchWithInput(String input) {
		// TODO Auto-generated method stub
		List<DiagnosticTest> list = dtDao.serachWithInput(input);
		return list;
	}

	public List<DiagnosticTest> findAll(){
		List<DiagnosticTest> list = dtDao.findAll();
		return list;
	}
	
	public boolean addDiagnostic(DiagnosticTest dt){
		return dtDao.addDiagnostic(dt);
	}
	
	public boolean deleteDiagnostic(DiagnosticTest dt){
		return dtDao.deleteDiagnostic(dt);
	}
	public DiagnosticTest findDiagnosticById( int dt_id ){
		return dtDao.findDiagnosticById(dt_id);
	}
}

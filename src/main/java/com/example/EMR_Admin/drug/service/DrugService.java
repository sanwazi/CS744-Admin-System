package com.example.EMR_Admin.drug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.drug.dao.DrugDao;
import com.example.EMR_Admin.drug.data.Drug;

@Service
public class DrugService {

	@Autowired
	DrugDao drugDao;
	
	public List<Drug> searchWithInput( String input ){
		return drugDao.searchWithInput(input);
	}
}

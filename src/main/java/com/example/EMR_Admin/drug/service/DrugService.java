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
	
	public List<Drug> getAll(){
		return drugDao.getAll();
	}
	
	public List<Drug> getDrugById(int drug_id){
		return drugDao.getDrugById(drug_id);
	}
	
	public boolean addDrug(Drug drug){
		return drugDao.addDrug(drug);
	}
	
	public boolean updateDrug(Drug drug){
		return drugDao.updateDrug(drug);
	}
	
	public boolean deleteDrug(Drug drug){
		int drug_id = drug.getDrug_id();
		return drugDao.deleteDrugById(drug_id);
	}
	
	public boolean addDrugList(List<Drug> input){
		return drugDao.addListDrug(input);
	}
}

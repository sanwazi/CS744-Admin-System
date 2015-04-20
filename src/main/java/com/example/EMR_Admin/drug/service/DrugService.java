package com.example.EMR_Admin.drug.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	
	public List<Drug> getDrugFromPharmacy(){
		List<Drug> drugList = new ArrayList<Drug>();
		try {
			URL pharmacyDrugList = new URL("http://138.49.101.83/Pharmacy/interface/drugList/all");
			URLConnection phDL = pharmacyDrugList.openConnection();
			BufferedReader in = new BufferedReader(
			                        new InputStreamReader(
			                        		phDL.getInputStream()));
			if (in.ready()) {
				try {
					JSONArray json = (JSONArray) new JSONParser().parse(in
							.readLine());
					
					for (int i = 0; i < json.size(); i++) {
						Drug d = new Drug();
						d.setDrug_id(Integer.parseInt (  (  (JSONObject)json.get(i)  ).get("id").toString()  )  );
						d.setDrug_lv((String) ((JSONObject) json.get(i))
								.get("drugLv"));
						d.setDrug_unique_id((String) ((JSONObject) json.get(i))
								.get("drugId"));
						d.setDrug_name_commercial((String) ((JSONObject) json
								.get(i)).get("drugNameCommercial"));
						d.setDrug_name_medical((String) ((JSONObject) json
								.get(i)).get("drugNameMedical"));
						d.setDrug_price( ((JSONObject) json.get(i))
								.get("drugPrice").toString());
						d.setDrug_reaction((String) ((JSONObject) json.get(i))
								.get("drugReaction"));
						d.setDrug_status((String) ((JSONObject) json.get(i))
								.get("drugStatus"));
						d.setDrug_type((String) ((JSONObject) json.get(i))
								.get("drugType"));
						d.setDrug_unit((String) ((JSONObject) json.get(i))
								.get("drugUnit"));
						d.setDrug_usage((String) ((JSONObject) json.get(i))
								.get("drugUsage"));
						d.setDrug_dose((String) ((JSONObject) json.get(i))
								.get("drugDose"));
						drugList.add(d);
					}
					return drugList;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return drugList;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drugList;
	}
}

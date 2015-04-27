package com.example.EMR_Admin.drug.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.authentication.service.CustomUserDetailsService;
import com.example.EMR_Admin.drug.dao.DrugDao;
import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.log.dao.LogDao;
import com.example.EMR_Admin.log.data.Log;

@Service
public class DrugService {

	@Autowired
	DrugDao drugDao;
	
	@Autowired
	LogDao logDao;
	
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
			URL pharmacyDrugJsonList = new URL("http://138.49.101.83/Pharmacy/interface/drugList/all");
			URLConnection pharmacy_drug_list = pharmacyDrugJsonList.openConnection();
			BufferedReader in = new BufferedReader(
			                        new InputStreamReader(
			                        		pharmacy_drug_list.getInputStream()));
			if (in.ready()) {
				try {
					JSONArray json = (JSONArray) new JSONParser().parse(in
							.readLine());
					
					for (int i = 0; i < json.size(); i++) {
						Drug d = new Drug();
						d.setDrug_id(Integer.parseInt (  (  (JSONObject)json.get(i)  ).get("id").toString()  )  );
						
						d.setDrug_unique_id((String) ((JSONObject) json.get(i))
								.get("drugId"));
						d.setDrug_type((String) ((JSONObject) json.get(i))
								.get("drugType"));
						d.setDrug_name_medical((String) ((JSONObject) json
								.get(i)).get("drugNameMedical"));
						d.setDrug_name_commercial((String) ((JSONObject) json
								.get(i)).get("drugNameCommercial"));					
						d.setDrug_price( ((JSONObject) json.get(i))
								.get("drugPrice").toString());
						d.setDrug_lv((String) ((JSONObject) json.get(i))
								.get("drugLv"));
						d.setDrug_dose((String) ((JSONObject) json.get(i))
								.get("drugDose"));
						d.setDrug_common_uses((String) ((JSONObject) json.get(i))
								.get("drugCommonUses"));
						d.setDrug_usage((String) ((JSONObject) json.get(i))
								.get("drugUsage"));
						d.setDrug_cautions((String) ((JSONObject) json.get(i))
								.get("drugCautions"));
						d.setDrug_description((String) ((JSONObject) json.get(i))
								.get("drugDescription"));
						d.setDrug_side_effects((String) ((JSONObject) json.get(i))
								.get("drugSideEffects"));
						d.setDrug_status((String) ((JSONObject) json.get(i))
								.get("drugStatus"));				
//						d.setDrug_unit((String) ((JSONObject) json.get(i))
//								.get("drugUnit"));
						d.setDrug_refill_cycle(((JSONObject) json.get(i))
								.get("drugRefillCycle").toString());
						d.setDrug_refill_times(((JSONObject) json.get(i))
								.get("drugRefillTimes").toString());
						drugList.add(d);
					}
					Log log = new Log();
					log.setAction("GET");
					log.setAdmin_name(CustomUserDetailsService.currentUserDetails().getUsername());
					log.setTable("Drug");
					log.setDate( new Date());
					logDao.addLog(log);
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

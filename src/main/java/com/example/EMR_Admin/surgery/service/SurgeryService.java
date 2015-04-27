package com.example.EMR_Admin.surgery.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.ConstantValue;
import com.example.EMR_Admin.authentication.service.CustomUserDetailsService;
import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.log.dao.LogDao;
import com.example.EMR_Admin.log.data.Log;
import com.example.EMR_Admin.surgery.dao.SurgeryDao;
import com.example.EMR_Admin.surgery.data.Surgery;

@Service
public class SurgeryService {

	@Autowired
	SurgeryDao sDao;
	
	@Autowired
	LogDao logDao;

	public List<Surgery> searchWithInput(String input) {
		// TODO Auto-generated method stub
		return sDao.serachWithInput(input);
	}
	
	public List<Surgery> getAll(){
		return sDao.getAll();
	}
	
	public List<Surgery> getById(int surgery_id){
		return sDao.getById(surgery_id);
	}
	
	public boolean addSurgery(Surgery surgery){
		JSONObject object = new JSONObject();
		object.put("table", "surgery");
		object.put("action","add");
		object.put("object",surgery);

		try {
			URL url = new URL(ConstantValue.INSTRANCEURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);
		    conn.setRequestProperty ("Authorization", "charset=utf-8");  
		    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		    writer.write(object.toString());
		    writer.flush();
		    String line;
		    BufferedReader reader = new BufferedReader(new 
		                                     InputStreamReader(conn.getInputStream()));
		    while ((line = reader.readLine()) != null) {
		      System.out.println(line);
		    }
		    writer.close();
		    reader.close();
		} catch (MalformedURLException e) {

	        e.printStackTrace();
	        
	    }
	    catch (IOException e) {

	        e.printStackTrace();
	       
	    } 
		Log log = new Log();
		log.setAction("ADD");
		log.setAdmin_name(CustomUserDetailsService.currentUserDetails().getUsername());
		log.setTable("Surgery");
		log.setDate( new Date());
		logDao.addLog(log);
		return sDao.addSurgery(surgery);
	}
	
	public boolean updateSurgery(Surgery surgery){
		JSONObject object = new JSONObject();
		object.put("table", "surgery");
		object.put("action","update");
		object.put("object",surgery);

		try {
			URL url = new URL(ConstantValue.INSTRANCEURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);
		    conn.setRequestProperty ("Authorization", "charset=utf-8");  
		    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		    writer.write(object.toString());
		    writer.flush();
		    String line;
		    BufferedReader reader = new BufferedReader(new 
		                                     InputStreamReader(conn.getInputStream()));
		    while ((line = reader.readLine()) != null) {
		      System.out.println(line);
		    }
		    writer.close();
		    reader.close();
		} catch (MalformedURLException e) {

	        e.printStackTrace();
	        
	    }
	    catch (IOException e) {

	        e.printStackTrace();
	       
	    } 
		Log log = new Log();
		log.setAction("UPD");
		log.setAdmin_name(CustomUserDetailsService.currentUserDetails().getUsername());
		log.setTable("Surgery");
		log.setDate( new Date());
		logDao.addLog(log);
		return sDao.updateSurgery(surgery);
	}
	
	public boolean deleteSurgery(Surgery surgery){
		int surgery_id = surgery.getSurgery_id();
		JSONObject object = new JSONObject();
		object.put("table", "surgery");
		object.put("action","delete");
		object.put("object",surgery);

		try {
			URL url = new URL(ConstantValue.INSTRANCEURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true);
		    conn.setRequestProperty ("Authorization", "charset=utf-8");  
		    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		    writer.write(object.toString());
		    writer.flush();
		    String line;
		    BufferedReader reader = new BufferedReader(new 
		                                     InputStreamReader(conn.getInputStream()));
		    while ((line = reader.readLine()) != null) {
		      System.out.println(line);
		    }
		    writer.close();
		    reader.close();
		} catch (MalformedURLException e) {

	        e.printStackTrace();
	        
	    }
	    catch (IOException e) {

	        e.printStackTrace();
	       
	    } 
		Log log = new Log();
		log.setAction("DEL");
		log.setAdmin_name(CustomUserDetailsService.currentUserDetails().getUsername());
		log.setTable("Surgery");
		log.setDate( new Date());
		logDao.addLog(log);
		return sDao.deleteSurgeryById(surgery_id);
	}
}

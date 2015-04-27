package com.example.EMR_Admin.treatment.service;

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
import com.example.EMR_Admin.log.dao.LogDao;
import com.example.EMR_Admin.log.data.Log;
import com.example.EMR_Admin.treatment.dao.TreatmentDao;
import com.example.EMR_Admin.treatment.data.Treatment;

@Service
public class TreatmentService {

	@Autowired
	TreatmentDao tDao;
	
	@Autowired
	LogDao logDao;
	
	public List<Treatment> findAll(){
		return tDao.findAll();
	}
	
	public Treatment findTreatmentById( int id ){
		return tDao.getTreatmentById(id);
	}
	
	public boolean addTreatment( Treatment t ){
		JSONObject object = new JSONObject();
		object.put("table", "treatment");
		object.put("action","add");
		object.put("object",t);

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
		log.setTable("Treatment");
		log.setDate( new Date());
		logDao.addLog(log);
		return tDao.addTreatment( t); 
	}
	
	public boolean deleteTreatment( Treatment t ){
		JSONObject object = new JSONObject();
		object.put("table", "treatment");
		object.put("action","delete");
		object.put("object",t);

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
		log.setTable("Treatment");
		log.setDate( new Date());
		logDao.addLog(log);
		return tDao.deleteTreatment( t );
	}
	
	public boolean updateTreatment( Treatment t ){
		JSONObject object = new JSONObject();
		object.put("table", "treatment");
		object.put("action","update");
		object.put("object",t);

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
		log.setTable("Treatment");
		log.setDate( new Date());
		logDao.addLog(log);
		return tDao.updateTreatment(t);
	}
}

package com.example.EMR_Admin.diagnostic_test.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.ConstantValue;
import com.example.EMR_Admin.authentication.service.CustomUserDetailsService;
import com.example.EMR_Admin.diagnostic_test.dao.DiagnosticTestDao;
import com.example.EMR_Admin.diagnostic_test.data.DiagnosticTest;
import com.example.EMR_Admin.drug.data.Drug;
import com.example.EMR_Admin.log.dao.LogDao;
import com.example.EMR_Admin.log.data.Log;

@Service
public class DiagnosticTestServcie {

	@Autowired
	DiagnosticTestDao dtDao;
	
	@Autowired
	LogDao logDao;

	public List<DiagnosticTest> searchWithInput(String input) {
		// TODO Auto-generated method stub
		List<DiagnosticTest> list = dtDao.serachWithInput(input);
		return list;
	}

	public List<DiagnosticTest> findAll() {
		List<DiagnosticTest> list = dtDao.findAll();
		return list;
	}

	public boolean addDiagnostic(DiagnosticTest dt) {
		JSONObject object = new JSONObject();
		object.put("table", "diagnostic_test");
		object.put("action","add");
		object.put("object",dt);

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
		log.setTable("Diagnostic Test");
		log.setDate( new Date());
		logDao.addLog(log);
		return dtDao.addDiagnostic(dt);
	}
	
	public boolean deleteDiagnostic(DiagnosticTest dt){
		JSONObject object = new JSONObject();
		object.put("table", "diagnostic_test");
		object.put("action","delete");
		object.put("object",dt);

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
		log.setTable("Diagnostic Test");
		log.setDate( new Date());
		logDao.addLog(log);
		return dtDao.deleteDiagnostic(dt);
	}

	public DiagnosticTest findDiagnosticById(int dt_id) {
		return dtDao.findDiagnosticById(dt_id);
	}

	public boolean updateDiagnostic(DiagnosticTest dt) {
		JSONObject object = new JSONObject();
		object.put("table", "diagnostic_test");
		object.put("action","update");
		object.put("object",dt);

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
		log.setTable("Diagnostic Test");
		log.setDate( new Date());
		logDao.addLog(log);
		return dtDao.updateDiagnostic(dt);
	} 
}

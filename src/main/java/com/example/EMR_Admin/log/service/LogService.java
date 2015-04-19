package com.example.EMR_Admin.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.log.dao.LogDao;
import com.example.EMR_Admin.log.data.Log;

@Service
public class LogService {

	@Autowired
	LogDao logDao;
	
	public boolean addLog( Log log ){
		return logDao.addLog(log);
	}
	
	public List<Log> getAll(){
		return logDao.getAll();
	}
}

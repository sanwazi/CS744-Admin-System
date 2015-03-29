package com.example.EMR_Admin.patient.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.lang.Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.patient.dao.PatientDao;
import com.example.EMR_Admin.patient.data.Patient;
@Service
public class PatientService {
	@Autowired
	PatientDao pDao;
	final String dateFormat = "yyyy-MM-dd";
	
	public Patient getPatientById(int patientId){
		Patient patient = pDao.findPatientByPatientId(patientId);
		return patient;
	}
	
	public String dateToString(java.util.Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	public List<Patient> patientList(){
		List<Patient> list = pDao.patientList();
		return list;
	}
	
	public String computeAge(java.util.Date date){
		Calendar cal = Calendar.getInstance();

        if (cal.before(date)) {
            return  "cannot be born in future!";
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(date);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
               
            } else {
                age--;
            }
          } 
        }
        return age+"";
	}
}
 
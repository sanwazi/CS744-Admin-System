package com.physician.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.data.Physician;
import com.authentication.service.CustomUserDetailsService;
import com.physician.dao.PhysicianDao;

@Service
public class PhysicianService {
	@Autowired
	PhysicianDao physicianDao;

	public Physician currentPhysician() {
		String account = CustomUserDetailsService.currentUserDetails()
				.getUsername();
		System.out.println("current user = " + account);
		Physician physician = physicianDao.getPhysicianByAccount(account);
		return physician;
	}
	
	public List<Physician> searchWithInput( String input ){
		List<Physician> list = physicianDao.serachWithInput(input);
		return list;
	}
}

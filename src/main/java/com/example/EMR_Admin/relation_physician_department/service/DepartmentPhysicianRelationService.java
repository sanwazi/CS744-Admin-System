package com.example.EMR_Admin.relation_physician_department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.relation_physician_department.dao.DepartmentPhysicianRelationDao;
import com.example.EMR_Admin.relation_physician_department.data.DepartmentPhysicianRelation;

@Service
public class DepartmentPhysicianRelationService {
	
	@Autowired
	DepartmentPhysicianRelationDao drDao;
	
	public List<Integer> getPhysicianIdByRelationList(String dName){
		
		List<Integer> physicianIdList = null;
		List<DepartmentPhysicianRelation> dpr = drDao.getRelationByDepartmentName(dName);
		if (dpr != null) {
			for (DepartmentPhysicianRelation dr : dpr) {
				physicianIdList.add(dr.getPhysician_id());
			}
			return physicianIdList;
		}
		else return physicianIdList;
	}
}

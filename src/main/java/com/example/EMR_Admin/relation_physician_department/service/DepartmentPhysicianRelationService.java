package com.example.EMR_Admin.relation_physician_department.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EMR_Admin.relation_physician_department.dao.DepartmentPhysicianRelationDao;
import com.example.EMR_Admin.relation_physician_department.data.DepartmentPhysicianRelation;
import com.example.EMR_Admin.surgery.data.Surgery;
import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.physician.service.PhysicianService;

@Service
public class DepartmentPhysicianRelationService {
	
	@Autowired
	DepartmentPhysicianRelationDao drDao;
	@Autowired
	PhysicianService phyService;
	
	private List<Integer> getPhysicianIdByRelationList(String dName){
		
		List<Integer> physicianIdList = new ArrayList<Integer>();
		List<DepartmentPhysicianRelation> dpr = drDao.getRelationByDepartmentName(dName);
		if (!dpr.isEmpty()) {
			for (DepartmentPhysicianRelation dr : dpr) {
				physicianIdList.add(dr.getPhysician_id());
			}
			return physicianIdList;
		}
		else return physicianIdList;
	}
	
	public List<Physician> getPhysiciansByDepartment(String dName){
		List<Integer> phyIds = getPhysicianIdByRelationList(dName);
		return phyService.getPhysiciansByIds(phyIds);
	}

}

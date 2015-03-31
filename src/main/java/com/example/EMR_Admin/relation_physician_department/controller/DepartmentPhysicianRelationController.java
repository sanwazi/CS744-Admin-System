package com.example.EMR_Admin.relation_physician_department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.authentication.data.Physician;
import com.example.EMR_Admin.department.service.DepartmentService;
import com.example.EMR_Admin.physician.service.PhysicianService;
import com.example.EMR_Admin.relation_physician_department.data.DepartmentPhysicianRelation;
import com.example.EMR_Admin.relation_physician_department.service.DepartmentPhysicianRelationService;
import com.example.EMR_Admin.surgery.data.Surgery;

@Controller
public class DepartmentPhysicianRelationController {
	@Autowired
	DepartmentPhysicianRelationService dpService;
	@Autowired
	PhysicianService phyService;
	@Autowired
	DepartmentService dService;
	
	@RequestMapping(value = "/department/viewPhysicians", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Physician> viewPhysicians(
			@RequestParam(value = "department_name", required = true) String dName) {
		System.out.println("admin clicked "+dName+" department.");
		
		List<Physician> list = dpService.getPhysiciansByDepartment(dName);
		return list;
	}
	
	@RequestMapping(value = "/department_physician/autocomplete", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Physician> searchWithInput(
			@RequestParam(value = "input", required = true) String input) {
		List<Physician> list = dpService.getNonRegisteredPhysiciansByInputPhysicianName(input);
		return list;
	}
	
	//add
	@RequestMapping(value = "/department_physician/add", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addRelation(
			@RequestParam(value = "physician_id")String physician_id,
			@RequestParam(value = "physician_name") String physician_name,
			@RequestParam(value = "departmentName") String department_name
			) {
		if(physician_id == null){
			return "id_null";
		}
		else{
			int department_id = dService.getIdByDepartmentName(department_name);
			DepartmentPhysicianRelation newR = new DepartmentPhysicianRelation();
			newR.setDepartment_id(department_id);
			newR.setDepartment_name(department_name);
			newR.setPhysician_id(Integer.parseInt(physician_id));
			newR.setPhysician_name(physician_name);
			return dpService.addRelation(newR);
		}
		
	}
	
	@RequestMapping(value = "/department_physician/addByPhysicianName", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody String addRelationByphyName(
			@RequestParam(value = "physician_name") String physician_name,
			@RequestParam(value = "departmentName") String department_name
			) {
			if(phyService.getPhysicianByName(physician_name).isEmpty())
			return "noSuchPhysician";
			else {
				if(dpService.getRelationByPhysicianName(physician_name).isEmpty()){
					return "need_phyid";
				}
				else return "exists";
			} 
		
		
	}
	
	@RequestMapping(value = "/department_physician/getRelationByPhysicianId", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody DepartmentPhysicianRelation getRelationByPhysicianId(
			@RequestParam(value = "physician_id", required = true) int physician_id) {
		DepartmentPhysicianRelation dpr = dpService.getRelationByPhysicianId(physician_id);
		return dpr;
	}
	
	//delete relation by physician id
	@RequestMapping(value = "/department_physician/deleteRelationByPhysicianId", method = RequestMethod.GET)
	@Secured(value = {"ROLE_ADMIN"})
	public @ResponseBody String deleteRelationByPhysicianId(
			@RequestParam(value = "physician_id", required=true) int physician_id){
		boolean result = dpService.deleteRelationByPhysicianId(physician_id);
		if(result) return "s";
		else return "d";
	}
	
	//get Relations by physician name
	@RequestMapping(value = "/department_physician/getDepartmentNamesByPhysicianName", method = RequestMethod.GET)
	@Secured(value = {"ROLE_ADMIN"})
	public @ResponseBody List<DepartmentPhysicianRelation> getDepartmentNamesByPhysicianName(
			@RequestParam(value = "physician_name", required=true) String physician_name){
		List<DepartmentPhysicianRelation> list = dpService.getRelationByPhysicianName(physician_name);
		System.out.println("here we got the list"+list.toString());
		return list;
	}
}

package com.example.EMR_Admin.department.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.department.data.Department;
import com.example.EMR_Admin.department.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService dService;

	@RequestMapping(value = "/department/autocomplete", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Department> searchWithInput(
			@RequestParam(value = "input", required = true) String input) {
		List<Department> list = dService.searchWithInput(input);
		return list;
	}
	
	@RequestMapping(value = "/getDepartment", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Department> getDepartment() {
		List<Department> departments = dService.getAll();
		return departments;
	}
	
	@RequestMapping(value = "/getDepartmentDetails", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Department> getDepartmentDetails(String department_name) {
		List<Department> departments = dService.getDepartmentByName(department_name);
		return departments;
	}
}

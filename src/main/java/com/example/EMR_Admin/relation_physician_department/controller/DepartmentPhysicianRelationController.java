package com.example.EMR_Admin.relation_physician_department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.relation_physician_department.service.DepartmentPhysicianRelationService;

@Controller
public class DepartmentPhysicianRelationController {
	@Autowired
	DepartmentPhysicianRelationService dpService;
	
}

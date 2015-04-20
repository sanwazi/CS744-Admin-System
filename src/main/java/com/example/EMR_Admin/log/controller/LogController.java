package com.example.EMR_Admin.log.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EMR_Admin.log.data.Log;
import com.example.EMR_Admin.log.service.LogService;

@Controller
public class LogController {

	@Autowired
	LogService logService;

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN" })
	public @ResponseBody List<Log> getLogs() {
		return logService.getAll();	
	}


}

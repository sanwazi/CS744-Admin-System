package com.example.EMR_Admin.authentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

	@RequestMapping("/403")
	public String denied(ModelMap model) {

		return "redirect:/pages/403.html";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model, HttpServletResponse response) {
		model.put("error", error);
		response.setHeader("sessionstatus", "timeout");
		return "login";
	}

	// @RequestMapping(value = "/logout", method = RequestMethod.GET)
	// public String logout(HttpServletRequest request) {
	// HttpSession session = request.getSession(false);
	// session.invalidate();
	// return "login.html";
	// }

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {

		model.put("error", error);
		return "denied";
	}
}

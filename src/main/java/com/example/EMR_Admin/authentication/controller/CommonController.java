package com.example.EMR_Admin.authentication.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	private static final Logger logger = LoggerFactory
			.getLogger(CommonController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

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

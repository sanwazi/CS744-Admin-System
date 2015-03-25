package com.authentication.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class DispatcherController {

	@RequestMapping("/roleDispatcher")
	public @ResponseBody
	void dispatcher(ModelMap model, HttpServletResponse response) {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(authorities.toString());
		try {
			if(authorities.toString().equals("[ROLE_ADMIN]")){
				response.sendRedirect("pages/crm.html");
				System.out.println("Admin login");
			}else if(authorities.toString().equals("[ROLE_AADMIN]")){
				response.sendRedirect("pages/viewGroup.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}

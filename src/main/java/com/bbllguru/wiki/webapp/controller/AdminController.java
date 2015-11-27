package com.bbllguru.wiki.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	String message = "Welcome to Admin page";
	 
	@RequestMapping("/admin")
	public ModelAndView showMessage(
		@RequestParam(value = "name", required = false, defaultValue = "Guest") String name) {
 
		ModelAndView mv = new ModelAndView("admin");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
}

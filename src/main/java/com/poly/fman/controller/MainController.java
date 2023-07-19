package com.poly.fman.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

	@RequestMapping("/fman")
	
	public String getIndexPage() {
		
		return "admin/dashboard";
	}
}

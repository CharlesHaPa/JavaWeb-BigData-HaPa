package com.sw1408.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/user/{pageName}")
	public String showUserPage(@PathVariable String pageName){
		return "user" + File.separator + pageName;
	}
	
	@RequestMapping("/main/{pageName}")
	public String showOrderPage(@PathVariable String pageName){
		return "main" + File.separator + pageName;
	}

	
	@RequestMapping("/ticket/{pageName}")
	public String showTicketPage(@PathVariable String pageName){
		return "ticket" + File.separator + pageName;
	}

	
	@RequestMapping("/item/{pageName}")
	public String showItemPage(@PathVariable String pageName){
		return "item" + File.separator + pageName;
	}
	
	@RequestMapping("/client/{pageName}")
	public String showClientPage(@PathVariable String pageName){
		return "client" + File.separator + pageName;
	}
	
	@RequestMapping("/admin/{pageName}")
	public String showAdminPage(@PathVariable String pageName){
		return "admin" + File.separator + pageName;
	}

	@RequestMapping("/echart/{pageName}")
	public String showEchartPage(@PathVariable String pageName){
		return "echart" + File.separator + pageName;
	}

}
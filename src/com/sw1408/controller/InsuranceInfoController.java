package com.sw1408.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sw1408.po.InsuranceInfo;
import com.sw1408.service.InsuranceService;

@Controller
public class InsuranceInfoController {
	@Resource
	InsuranceService insuranceService;
	
	@RequestMapping("/client/insurance")
	public ModelAndView showInsuranceInfo(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		List<InsuranceInfo> list = insuranceService.showInsuranceInfo((Integer)request.getSession().getAttribute("clientId"));
		modelAndView.addObject("CurrentInsurance",list);
		modelAndView.setViewName("/client/insurance");
		return modelAndView;
		
	}
}

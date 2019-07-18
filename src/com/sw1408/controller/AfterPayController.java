package com.sw1408.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sw1408.vo.TrainSeatVo;

@Controller
public class AfterPayController {
	@RequestMapping(value = "/AfterPay")
	public ModelAndView register(HttpServletRequest request, @ModelAttribute TrainSeatVo trainSeatVo, Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView();	
		modelAndView.setViewName("ticket/success");
		return modelAndView;
	}
}

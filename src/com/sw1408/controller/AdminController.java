package com.sw1408.controller;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sw1408.service.AdminService;

@Controller
public class AdminController {
	@Resource AdminService adminService;
	
	@RequestMapping(value="/admin/main")
	public ModelAndView adminMain(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/login");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/index")
	public ModelAndView adminIndex(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/index");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/on")
	public String adminLogin(HttpServletRequest request){
		String userName;
		String psw;
		System.out.println("in");
		userName = request.getParameter("userName");
		psw = request.getParameter("psw");
		int flag = adminService.adminLogin(userName, psw);
		if(flag == 1){
			request.getSession().setAttribute("flagUserName",userName);
			return "{\"flag\":"+"\"fail1\"}";
		}else{
			return "{\"flag\":"+"\"fail\"}";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/logout",produces="text/json;charset=UTF-8")
	public String adminLogout(HttpServletRequest request){
		request.getSession().setAttribute("flagUserName",null);
		return "true";
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/famale", produces = "text/json;charset=UTF-8")
	public String getFamaleAnalysis(HttpServletRequest request) {
		if (request.getSession().getAttribute("famaleResult"+request.getParameter("year")) != null) {
			return (String) request.getSession().getAttribute("famaleResult"+request.getParameter("year"));
		} else {
			String year = (String) request.getParameter("year");
			System.out.println("year=" + year);
			String result = adminService.getFamaleAnalysis(year);
			System.out.println("famale=" + result);
			request.getSession().setAttribute("famaleResult"+year, result);
			request.getSession().setAttribute("year", year);
			return result;
		}
		
	}

	@ResponseBody
	@RequestMapping(value = "/admin/male", produces = "text/json;charset=UTF-8")
	public String getMaleAnalysis(HttpServletRequest request) {
		if (request.getSession().getAttribute("maleResult"+request.getParameter("year")) != null) {
			return (String) request.getSession().getAttribute("maleResult"+request.getParameter("year"));
		} else {
			String year = (String) request.getParameter("year");
			System.out.println("year=" + year);
			String result = adminService.getMaleAnalysis(year);
			System.out.println("male=" + result);
			request.getSession().setAttribute("maleResult"+year, result);
			request.getSession().setAttribute("year", year);
			return result;
		}
	}

	@RequestMapping("admin/count")
	@ResponseBody
	public List<String> itemCount(@RequestParam String countByYear, HttpServletRequest request) {
		
		if(request.getSession().getAttribute("result"+countByYear) != null) {
			String list_tmp = (String) request.getSession().getAttribute("result"+countByYear);
			list_tmp = list_tmp.substring(1, list_tmp.length()-1);
			List<String> list = Arrays.asList(list_tmp.split(","));
			System.out.println("sessionList = " + list);
			return list;
			//return (String) request.getSession().getAttribute("result"+countByYear);
		} else {
			System.out.println(countByYear);
			List<String> list = adminService.countItemByHour(countByYear);
			System.out.println("list:" + list);
			String list_tmp = list.toString();
			System.out.println(list_tmp);
			request.getSession().setAttribute("result"+countByYear, list_tmp);
			request.getSession().setAttribute("year", countByYear);
			System.out.println(request.getSession().getAttribute("result"+countByYear));
			return list;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/admin/showTicketsNumGroupByCity", produces = "text/json;charset=UTF-8")
	public String showTicketsNumGroupByCity(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("finalResult"+request.getParameter("year")) != null) {
			return (String) request.getSession().getAttribute("finalResult"+request.getParameter("year"));
		} else {
			System.out.println(request);
			String year = (String) request.getParameter("year");
			System.out.println("year=" + year);
			JSONArray result = null;
			ResultSet resultSet = adminService.showTop5TicketsNumGroupByCity(year);
			int allNum = 0;
			int num = 0;
			int extraNum = 0;
			String finalResult = null;
			Map<String, Integer> map = new HashMap<>();

			try {
				allNum = adminService.showAllTicketsNum(year);
				while (resultSet.next()) {
					num += resultSet.getInt("num");
					map.put(resultSet.getString("city"), resultSet.getInt("num"));
				}
				System.out.println(num);
				extraNum = allNum - num;
				System.out.println(extraNum);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			System.out.println(map);

			try {
				result = JSONArray.fromObject(map);
				finalResult = result.toString().substring(0, result.toString().length() - 1) + ",{\"num\":\"" + extraNum
						+ "\",\"city\":\"其他\"}]";
				System.out.println(finalResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("finalResult"+year, finalResult);
			request.getSession().setAttribute("year", year);
			return finalResult;
		}

	}

	/*@RequestMapping("/admin/aaa")
	public ModelAndView haha() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/showTicketsNumGroupByCity");
		return modelAndView;
	}*/
}
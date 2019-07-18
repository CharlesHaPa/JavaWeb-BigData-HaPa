package com.sw1408.controller;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.yarn.server.resourcemanager.webapp.dao.NewApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sw1408.service.AdminService;
import net.sf.json.JSONArray;



@Controller
public class ChartController {
	@Resource
	AdminService adminService;
	@RequestMapping(value = "/seatRate")
	public ModelAndView seatRate(Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView();	
		modelAndView.setViewName("admin/seatRate");
		return modelAndView;
	}
	@RequestMapping(value = "/dbInfo")
	public ModelAndView dbInfo(Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView();	
		modelAndView.setViewName("admin/dbInfo");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/getSeatInfo",produces="html/text;charset=UTF-8")
	public String  getSeatInfo(HttpServletRequest request) throws Exception {
		if (request.getSession().getAttribute("seatInfoResult"+request.getParameter("departure")+request.getParameter("arrival")+request.getParameter("date")) != null) {
			String result = (String) request.getSession().getAttribute("seatInfoResult"+request.getParameter("departure")+request.getParameter("arrival")+request.getParameter("date"));
			return result;
		} else {
			String result = adminService.getSeatInfo(request.getParameter("departure")
					,request.getParameter("arrival"),request.getParameter("date"));
			String departure = (String) request.getParameter("departure");
			System.out.println(departure);
			String arrival = (String) request.getParameter("arrival");
			System.out.println(arrival);
			String date = (String) request.getParameter("date");
			System.out.println(date);
			request.getSession().setAttribute("seatInfoResult"+departure+arrival+date, result);
			request.getSession().setAttribute("date", date);
			return result;
		}
	}
	@RequestMapping(value = "/cmdPage")
	public ModelAndView cmdPage(Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView();	
		modelAndView.setViewName("admin/cmd");
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value = "/getTable",produces="html/text;charset=UTF-8")
	public String getTable(HttpServletRequest request){
		List<String> tables =new ArrayList<String>();
		try{
			tables = adminService.getTables(request.getParameter("location"),request.getParameter("db"));
		}catch(Exception e){
			tables = adminService.getTables(request.getParameter("location"),"default");
		}
		JSONArray json = JSONArray.fromObject(tables);
		return json.toString();
	}
	@ResponseBody
	@RequestMapping(value = "/runCmd",produces="html/text;charset=UTF-8")
	public String runCmd(HttpServletRequest request){
		adminService.runCmd(request.getParameter("cmd"),request.getParameter("tableName"),
				request.getParameter("db"),request.getParameter("dir"));
		return null;
	}
	@ResponseBody
	@RequestMapping(value = "/delTable")
	public Boolean delTable(HttpServletRequest request){
		String[] data=request.getParameter("del").split(",");
		for(int i=0;i<data.length;i++){	
			if(!data[i].equals(" ")){
				System.out.println(data[i]);
				adminService.delTable(data[i],request.getParameter("db"));
			}
		}	
		return true;
	}
	@ResponseBody
	@RequestMapping(value = "/getDbs")
	public String getDbs(HttpServletRequest request){
		List<String>dbStrings = adminService.getDbs();
		JSONArray json = JSONArray.fromObject(dbStrings);
		System.out.println(json.toString());
		return json.toString();
	}
	@ResponseBody
	@RequestMapping(value = "/getStructure")
	public String getStructure(HttpServletRequest request){
		List<String>dbStrings = adminService.getStructure(request.getParameter("db"),
				request.getParameter("table"));
		JSONArray json = JSONArray.fromObject(dbStrings);
		System.out.println(json.toString());
		return json.toString();
	}
	@ResponseBody
	@RequestMapping(value = "/addTable")
	public Boolean addTable(HttpServletRequest request){
		String[] data=request.getParameter("add").split(",");
		for(int i=0;i<data.length;i++){	
			if(!data[i].equals(" ")){
				System.out.println(data[i]);
				adminService.addTables(data[i],request.getParameter("dir"),request.getParameter("db"));
			}
		}	
		return true;
	}
}

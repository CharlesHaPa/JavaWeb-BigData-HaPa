package com.sw1408.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.sw1408.po.Client;
import com.sw1408.service.ClientService;
import com.sw1408.service.TrainService;
import com.amazonaws.services.ec2.model.transform.RequestSpotInstancesResultStaxUnmarshaller;
import com.github.pagehelper.PageInfo;
import com.sw1408.po.Passenger;
import com.sw1408.util.JsonDateValueProcessor;
import com.sw1408.util.PageParams;

import net.sf.json.JSONObject;

@Controller
public class ClientController {
	
	@Resource ClientService clientService;
	@Autowired TrainService trainService;
	@Resource  PageParams pageParams;
	

	
	@RequestMapping(value="/client/register",method= RequestMethod.POST)
	public ModelAndView clientRegister(HttpServletRequest request, @ModelAttribute Client client){
		if(clientService.checkRepeat(client) == 1 && clientService.clientRegister(client)){
			
			Client result=clientService.login(client.getCardId(), client.getPsw());
		
			request.getSession().setAttribute("clientId", result.getId());
			request.getSession().setAttribute("clientName", result.getUserName());
			request.getSession().setAttribute("clientGender", result.getGender());
			ModelAndView modelAndView = new ModelAndView("redirect:/main/index");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("registerFailed", "failed");
			modelAndView.setViewName("/client/regist");
			return modelAndView;
		}
	}
	

	@ResponseBody
	@RequestMapping(value="/client/login",method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String login(HttpServletRequest request) throws IOException{

		Client result=clientService.login(request.getParameter("userName"), request.getParameter("psw"));
		if(result!=null){
			request.getSession().setAttribute("clientId", result.getId());
			request.getSession().setAttribute("clientName", result.getUserName());
			request.getSession().setAttribute("clientGender", result.getGender());
			System.out.println("login!");
			return JSONObject.fromObject(result,JsonDateValueProcessor.getJsonConfig()).toString();
		}else {
			return "{\"result\":"+"\"no\"}";
		}
	}

	@ResponseBody
	@RequestMapping(value="/client/logout",produces="text/json;charset=UTF-8")
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("clientId", null);
		request.getSession().setAttribute("clientName", null);
		request.getSession().setAttribute("clientGender", null);
		return "{\"result\":"+"\"true\"}";
	}
	/**
	 * 璋冪敤ClientService涓嬬殑selectAllPassangers()鏂规硶锛岄�杩囪鐢ㄦ埛鐨刬d鏌ヨ璇ョ敤鎴蜂笅鎵�湁涔樺淇℃伅
	 * 
	 * @author SXY
	 * @return ModelAndView
	 */
	@RequestMapping("/client/frequentContactsTable")
	public ModelAndView frequentContactsTable(HttpSession session, HttpServletRequest request) {
		int pageNum;
		ModelAndView modelAndView = new ModelAndView();
		if ("null".equals(request.getParameter("pageNum")) || request.getParameter("pageNum") == null) {
			pageNum = 1;
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		pageParams.setPageSize(7);
		pageParams.setPageNum(pageNum);
		List<Passenger> list = clientService.selectAllPassengers(pageParams,(Integer)request.getSession().getAttribute("clientId"));
		PageInfo<Passenger> pageInfo = new PageInfo<Passenger>(list);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo",pageInfo);
		modelAndView.setViewName("/client/frequentContactsTable");
		return modelAndView;
	}

	
	/**
	 * 璋冪敤ClientService涓嬬殑addPassenger()鏂规硶锛屾坊鍔犱竴涓箻瀹�
	 * 
	 * @author SXY
	 * @param name
	 *            Passenger's name
	 * @param cardId
	 *            Passenger's cardId
	 * @param gender
	 *            Passenger's gender
	 * @param isStudent
	 *            Mark passenger's type
	 * @return ModelAndView
	 */

	@ResponseBody
	@RequestMapping(value = "/client/addPassenger", method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String addPassenger(@ModelAttribute Passenger passenger, HttpServletRequest request) {
		
		Client client = new Client();
		client.setId((Integer)request.getSession().getAttribute("clientId"));
		//int flag = 1;
		boolean result = false;
		try{
			result = clientService.addPassenger(passenger, client);
			//flag = 1;
		}catch(Exception e){
			//flag = 0;
		}
		if (result == true) {
			
			return "{\"result\":"+"\"true\"}";
			
		} else {
			
			return "{\"result\":"+"\"false\"}";
		}
	}
	
	/**
	 * 璋冪敤ClientService涓嬬殑updatePassengerInfo()鏂规硶锛屼慨鏀逛竴涓箻瀹俊鎭�
	 * 
	 * @author SXY
	 * @param name
	 *            Passenger's name
	 * @param cardId
	 *            Passenger's cardId
	 * @param gender
	 *            Passenger's gender
	 * @param isStudent
	 *            Mark passenger's type
	 * @return ModelAndView
	 */
	
	@ResponseBody
	@RequestMapping(value = "/client/modifyFrequentContactInfo", method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String updatePassenger(@ModelAttribute Passenger passenger, HttpServletRequest request) {
		
		System.out.println(passenger);
		int flag = 1;
		try{
			boolean result = clientService.updatePassengerInfo(passenger);
			flag=1;
		}catch(Exception e){
			flag=0;
		}
		if (flag == 1) {
			return "{\"result\":"+"\"yes\"}";
		} else {
			return "{\"result\":"+"\"no\"}";
		}
	}
	
	/**
	 * 鍒犻櫎涔樺
	 * 
	 * @author SXY
	 * @param passenger
	 * @return
	 */
	@RequestMapping("/client/deletePassenger")
	public ModelAndView deletePassenger(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		String cardId = request.getParameter("cardId");
		System.out.println(cardId);
		System.out.println(request.getParameter("isStudent"));
		int isStudent = Integer.parseInt(request.getParameter("isStudent"));
		System.out.println("cardId=" +cardId + "   isStudent=" + isStudent);
		boolean result = clientService.deletePassenger(cardId, isStudent);
		System.out.println(result);
		if (result) {
			modelAndView.setViewName("/client/showFrequentContacts");
			return modelAndView;
		} else {
			modelAndView.setViewName("/client/error_deletePassenger");
			return modelAndView;
		}
	}
	
	/**
	 * test
	 * 
	 * @author SXY
	 * @return
	 */
	@RequestMapping("/client/test1")
	public ModelAndView testPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("name", request.getParameter("name"));
		modelAndView.addObject("gender", request.getParameter("gender"));
		modelAndView.addObject("cardId", request.getParameter("cardId"));
		modelAndView.addObject("isStudent", request.getParameter("isStudent"));
		modelAndView.setViewName("/client/modifyFrequentContactInfo");
		return modelAndView;
	}

	
	@RequestMapping("/main/test")
	public ModelAndView test(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/main/index");	
		return modelAndView;
	}
	
	@RequestMapping("/client/regist")
	public ModelAndView regist(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/client/regist");
		return modelAndView;
	}
	
	@RequestMapping("/client/query")
	public ModelAndView queryClient(HttpServletRequest request){
		
			Client client=clientService.queryClient((Integer)request.getSession().getAttribute("clientId"));
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("CurrentClient", client);
			modelAndView.setViewName("/client/info");
			return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/modify",method=RequestMethod.POST,produces="text/json;charset=UTF-8")
	public String modifyClientInfo(Client client,HttpServletRequest request){
		client.setId((Integer)request.getSession().getAttribute("clientId"));
		int id = (Integer)request.getSession().getAttribute("clientId");
		int flag = clientService.checkRepeatNotAll(client,id);
		if(flag == 1){
			try{
				clientService.modifyClient(client);
			}catch(Exception e){
				e.printStackTrace();
			}
			return "{\"result\":"+"\"yes\"}";
		}else{
			return "{\"result\":"+"\"no\"}";
		}
	}
	
	
}

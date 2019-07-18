package com.sw1408.controller;
import java.util.LinkedList;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sw1408.po.Passenger;
import com.sw1408.service.TicketService;
import com.sw1408.vo.AvailableTrain;
import com.sw1408.vo.TicketVo;
import net.sf.json.JSONObject;


@Controller
public class TicketController {

	@Resource
	TicketService ticketService;
	
	@RequestMapping("ticket/queryTicket")
	public ModelAndView queryTicket(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		LinkedList<AvailableTrain> result = new LinkedList<AvailableTrain>();
		try {
			result = ticketService.queryTicket(request.getParameter("fromStation"), 
						request.getParameter("toStation"), request.getParameter("departDate"));
		} catch (Exception e) {

			e.printStackTrace();
			modelAndView .setViewName("ticket/error");
			return modelAndView;
		}
		
		modelAndView .setViewName("ticket/resultList_"+request.getParameter("queryType"));
		modelAndView.addObject("trains",result);
		return modelAndView;
	}


	@RequestMapping(value = "/payTicket",produces="html/text;charset=UTF-8")
	@ResponseBody
	public String payTicket(HttpServletRequest request, @ModelAttribute TicketVo ticketVo) 
					throws Exception {
		try{
			ticketVo.setClientId((Integer)request.getSession().getAttribute("clientId"));
			return ticketService.payTicket(ticketVo);
		}catch(Exception e){
			e.printStackTrace();
			return null;	
		}		
	}

	//@RequestParam会将值匹配到id属性里
	@ResponseBody
	@RequestMapping(value="/returnTicket",method= RequestMethod.POST)
	public boolean returnTicket(@RequestParam int itemId){
		System.out.println("do");
		boolean result = ticketService.returnTicket(itemId);
		return result;

	}

	
	@RequestMapping(value = "ticket/endorse")
	public String endorse(@RequestParam String isEndorse,int endorseItemId, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("endorseItemId", endorseItemId);
		session.setAttribute("isEndorse", isEndorse);
		return "ticket/query";
	}
	
	//注解@Transactional (propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)很重要,现在只要该方法出现了异常就会发生回滚
	@ResponseBody
	@RequestMapping(value="/endorseTicket")
	@Transactional (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)  
	public boolean endorseTicket(HttpServletRequest request, @ModelAttribute TicketVo ticketVo) throws Exception{
	    System.out.println("here run");	
    	//买票
    	ticketVo.setClientId((Integer)request.getSession().getAttribute("clientId"));
    	String payResult = ticketService.payTicket(ticketVo);
		System.out.println(payResult+"payresult");
		//将payResult放入session中
		HttpSession session = request.getSession();
		session.setAttribute("payResult1", payResult);
		System.out.println("哈哈"+session.getAttribute("payResult1"));
		//调用退票controller
		boolean returnResult = returnTicket((int)request.getSession().getAttribute("endorseItemId"));
		System.out.println("returnResult"+returnResult);
		
		if(null != payResult && returnResult==true){
			return true;
		}
		else{
			return false;
		}
	}
		
	@RequestMapping(value="ticket/removeEndorse")
	public void cancelEndorse(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("isEndorse");
		session.removeAttribute("endorseItemId");
	}
	
	@ResponseBody
	@RequestMapping(value="ticket/endorseInfo" ,produces="text/json;charset=UTF-8")
	public String endorseInfo(@RequestParam int endorseItemId){
		System.out.println("do successfully"+endorseItemId);
		
		Passenger passenger = ticketService.endorseInfo(endorseItemId);
		if(null != passenger){
			JSONObject jsonObject = JSONObject.fromObject(passenger);
			System.out.println(jsonObject);
			return jsonObject.toString();
		}
		else{
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ticket/seatInfo",produces="html/text;charset=UTF-8")
	public String seatInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		String result = (String)session.getAttribute("payResult1");
		System.out.println("11"+result);
		return result;
	}
}

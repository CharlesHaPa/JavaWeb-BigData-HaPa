package com.sw1408.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sw1408.po.Passenger;
import com.sw1408.service.SelectSeatService;
import com.sw1408.vo.SeatVo;
import com.sw1408.vo.TrainSeatVo;

@Controller
public class PurchaseController{
	@Resource
	SelectSeatService selectSeatService;
	@RequestMapping(value = "/selectSeat")
	public ModelAndView register(HttpServletRequest request, @ModelAttribute TrainSeatVo trainSeatVo, Model model) throws Exception {
		ModelAndView modelAndView=new ModelAndView();
		SeatVo seatVo=selectSeatService.selectSeat(trainSeatVo);
		try{
			List<Passenger> passengers= selectSeatService.selectPassengers((Integer)request.getSession().getAttribute("clientId"));
			modelAndView.addObject(seatVo);
			if(passengers!=null){
				modelAndView.addObject("passengers",passengers);
			}								
		}catch (Exception e) {
			
		}
		modelAndView.setViewName("ticket/buy");
		return modelAndView;
		
	}
	
}
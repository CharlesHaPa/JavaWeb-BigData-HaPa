package com.sw1408.service;
import java.util.LinkedList;

import com.sw1408.po.Passenger;
import com.sw1408.vo.AvailableTrain;
import com.sw1408.vo.TicketVo;



public interface TicketService {

	public  LinkedList<AvailableTrain> queryTicket(String fromStation,String toStation,String departDate) throws Exception;

	public String payTicket(TicketVo ticketVo);
	
	public boolean returnTicket(int itemId);
	
	public Passenger endorseInfo(int itemId);
}

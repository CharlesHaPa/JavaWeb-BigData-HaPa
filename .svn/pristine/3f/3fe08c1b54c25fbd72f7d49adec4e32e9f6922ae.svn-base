package com.sw1408.mapper;

import com.sw1408.po.Ticket;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.hive.ql.parse.HiveParser_IdentifiersParser.intervalLiteral_return;



public interface TicketMapper {
	public void createTicket(Ticket ticket);

	public void updateSeat(int seatId, String usable);

	public void createItem(int clientId, int seatId, int departure, int arrival, String status, Date createDate,
			Date payDate, double totalFee);

	public void addInsurance(int clientId, int seatId, int passengerid,int departure,int arrival,int insuranceId);
	public int updateTicketStatus(int ticketId,String status);
	public Ticket selectTicketById(int id);
	
	List<Ticket> selectTicketByPassengerId(int passengerId);

	List<Ticket> selectTicketByDeparture(int stationId);
}

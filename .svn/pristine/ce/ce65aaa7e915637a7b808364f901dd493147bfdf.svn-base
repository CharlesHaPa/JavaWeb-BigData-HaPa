package com.sw1408.service.impl;

import com.sw1408.mapper.*;
import com.sw1408.po.*;
import com.sw1408.service.ItemService;
import com.sw1408.vo.ItemDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DLETC on 2017/7/4.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private SeatMapper seatMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private TrainMapper trainMapper;

    @Override
    public ItemDetail getItemDetail(int id) {
        Item item = itemMapper.selectItemById(id);
        return getItemDetail(item);
    }

    @Override
    public ItemDetail getItemDetail(Item item) {
        Ticket ticket = ticketMapper.selectTicketById(item.getTicketId());
        ItemDetail itemDetail = new ItemDetail();
        Seat seat = seatMapper.selectSeatById(ticket.getSeatId());
        Passenger passenger =passengerMapper.selectPassengerById(ticket.getPassengerId());
        Station departStation = stationMapper.selectStationById(ticket.getDeparture());
        Station arriveStation = stationMapper.selectStationById(ticket.getArrival());
        Schedule schedule = scheduleMapper.selectScheduleByTrainIdAndStationId(seat.getTrainId(), ticket.getDeparture());
        Train train = trainMapper.selectTrainById(seat.getTrainId());
        itemDetail.setOrderId(item.getId());
        itemDetail.setClientId(item.getClientId());
        itemDetail.setOrderStatus(item.getStatus());
        itemDetail.setTicketId(ticket.getId());
        itemDetail.setCreateTime(item.getCreateDate());
        itemDetail.setSeatInfo(seat.getCarriage()+"车" + seat.getRow_num() + seat.getCol_num() + seat.getType());
        itemDetail.setPassengerName(passenger.getName());
        itemDetail.setPassengerCardId(passenger.getCardId());
        itemDetail.setTicketType(ticket.getType());
        itemDetail.setTicketStatus(ticket.getStatus());
        itemDetail.setPrice(ticket.getPrice());
        itemDetail.setDeparture(departStation.getName());
        itemDetail.setArrival(arriveStation.getName());
        itemDetail.setDepartTime(schedule.getDepartTime());
        itemDetail.setTrainName(train.getTrainName());
        return itemDetail;
    }

    @Override
    public List<ItemDetail> queryItemDetailByClientId(int clientId) {
        List<Item> items = itemMapper.selectItemByClientId(clientId);
        List<ItemDetail> details = new ArrayList<>();
        for (Item item: items) {
            details.add(getItemDetail(item));
        }
        return details;
    }

    @Override
    public List<ItemDetail> queryItemDetailByStatusAndCreateDateRange(String status, java.sql.Date startDate, java.sql.Date endDate) {
        List<Item> items = itemMapper.selectItemsByStatusAndCreateDateRange(status, startDate, endDate);
        List<ItemDetail> details = new ArrayList<>();
        for (Item item: items) {
            details.add(getItemDetail(item));
        }
        return details;
    }

   
    @Override
    public List<ItemDetail> queryItemDetailByPassengerName(String passengerName) {
        List<Passenger> passengers = passengerMapper.selectPassengerByName(passengerName);
        List<ItemDetail> details = new ArrayList<>();
        for (Passenger passenger:  passengers) {
            List<Ticket> tickets = ticketMapper.selectTicketByPassengerId(passenger.getId());
            for (Ticket tiket: tickets) {
                List<Item> items = itemMapper.selectItemByTicketId(tiket.getId());
                for (Item item: items) {
                    ItemDetail detail = getItemDetail(item);
                    details.add(detail);
                }
            }
        }
        return details;
    }

    @Override
    public List<ItemDetail> queryItemDetailByClientIdAndStatusAndCreateDateRange(int clientId, String status, java.sql.Date startDate, java.sql.Date endDate) {
        List<ItemDetail> details = new ArrayList<>();
        List<Item> items = itemMapper.selectItemByClientIdAndStatusAndCreateDateRange(clientId, status, startDate, endDate);
        for (Item item: items) {
            details.add(getItemDetail(item));
        }
        return details;
    }
    
    @Override
    public List<ItemDetail> queryItemDetailByClientIdAndDepartDateRange(int clientId, java.sql.Date startDate, java.sql.Date endDate) {
		List<ItemDetail> details = new ArrayList<>();
		List<Item> items = itemMapper.selectItemByClientId(clientId);
		for (Item item: items) {
			details.add(getItemDetail(item));
		}
		for (int i = 0; i < details.size();) {
			ItemDetail detail = details.get(i);
			if (startDate != null) {
				if (new java.sql.Date(detail.getDepartTime().getTime()).before(startDate)) {
					details.remove(i);
					continue;
				}
			}
			if (endDate != null) {
				if (new java.sql.Date(detail.getDepartTime().getTime()).after(endDate)) {
					details.remove(i);
					continue;
				}
			}
			i++;
		}
    	return details;
    }
}

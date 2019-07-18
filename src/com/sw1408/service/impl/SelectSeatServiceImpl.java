package com.sw1408.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sw1408.mapper.SelectSeatMapper;
import com.sw1408.po.Passenger;
import com.sw1408.po.Seat;
import com.sw1408.service.SelectSeatService;
import com.sw1408.util.SeatUtil;
import com.sw1408.vo.SeatVo;
import com.sw1408.vo.TrainSeatVo;
@Service
public class SelectSeatServiceImpl implements SelectSeatService {
	@Resource 
	SelectSeatMapper selectSeatMapper;
	public SeatVo selectSeat(TrainSeatVo trainSeat) {
		// TODO Auto-generated method stub
		System.out.println(trainSeat);
		Integer departureid= selectSeatMapper.findStationId(trainSeat.getDeparture());
		Integer arriveid= selectSeatMapper.findStationId(trainSeat.getArrival());
		Date depart = selectSeatMapper.queryDepartTime(departureid,trainSeat.getTrainId());
		Date arrive = selectSeatMapper.queryArriveTime(arriveid,trainSeat.getTrainId());
		SimpleDateFormat day = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat time = new SimpleDateFormat("HH时mm分");
		String path = selectSeatMapper.queryPath(trainSeat.getTrainId());
		String needPath =SeatUtil.getNeedPath(path, departureid, arriveid);
		if(needPath==null){
			return null;
		}
		String during = SeatUtil.caculateTime(arrive, depart);
		Seat seat = selectSeatMapper.selectSeat(needPath,trainSeat.getTrainId(),trainSeat.getSeatType());
		Integer distance = selectSeatMapper.queryDistance(departureid,arriveid);
		double price=(double) 0.5;
		SeatVo seatVo=null;
		try{
			price = selectSeatMapper.queryCaculateMethod(trainSeat.getSeatType(), trainSeat.gettrainType());
			price*=(int)(distance/1000);
			seatVo = new SeatVo(seat.getId(),seat.getTrainId(),trainSeat.getSeatType(),
					seat.getRow_num(),seat.getCol_num(),seat.getCarriage(),trainSeat.getTrainName(),
					trainSeat.getDeparture(),trainSeat.getArrival(),
					time.format(depart),time.format(arrive),day.format(depart),day.format(arrive),during,price
					);
		}catch (Exception e){
			
		}	
		
		return seatVo;
	}
	public List<Passenger> selectPassengers(int clientId){
		return selectSeatMapper.selectPassegers(clientId);
	}
		
}
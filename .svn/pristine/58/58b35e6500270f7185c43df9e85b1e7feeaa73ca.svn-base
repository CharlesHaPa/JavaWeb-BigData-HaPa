package com.sw1408.mapper;

import java.util.Date;
import java.util.List;

import com.sw1408.po.Passenger;
import com.sw1408.po.Seat;
import com.sw1408.vo.TrainSeatVo;

public interface SelectSeatMapper {
	Date queryDepartTime(int departId,int trainId);
	Date queryArriveTime(int arriveId,int trainId);
	String queryPath(int trainId);
	Seat selectSeat(String needPath,int trainId,String type);
	Integer queryDistance(int depature,int arrival);
	String findStationName(int id);
	List<Passenger> selectPassegers(int clientId);
	int findStationId(String name);
	double queryCaculateMethod(String seatType,String trainType);
}

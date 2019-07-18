package com.sw1408.service;

import java.util.List;

import com.sw1408.po.Passenger;
import com.sw1408.vo.SeatVo;
import com.sw1408.vo.TrainSeatVo;

public interface SelectSeatService {
	public SeatVo selectSeat(TrainSeatVo trainSeat);
	public List<Passenger> selectPassengers(int clientId);
}

package com.sw1408.mapper;

import com.sw1408.po.Seat;

/**
 * Created by DLETC on 2017/7/4.
 */
public interface SeatMapper {
    public int addSeat(Seat seat);
    public int updateSeatStatus(int seatId,String usable);
	public Seat selectOneSeatByid(int id);
	Seat selectSeatById(int id);
}
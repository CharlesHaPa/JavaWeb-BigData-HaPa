package com.sw1408.mapper;
import java.util.ArrayList;
import java.util.List;

import com.sw1408.util.AvailableSeat;

public interface QueryTicketMapper {

	List<Integer> queryTrains(String departTime,Integer fromStation,Integer toStation);
	List<AvailableSeat> querySeats(Integer fromStation,Integer toStation,Integer trainId);
	String getTrainName(Integer trainId);
	Integer getStationId(String stationName);
	String getTrainType(Integer trainId);
    ArrayList<String> getTypes(Integer trainId);
    String getStartDate(Integer trainId,Integer stationId);
    String getEndDate(Integer trainId,Integer stationId);
    Integer getIntervalMin(Integer trainId,Integer toStation,Integer fromStation);
    float getDistance(Integer stationA,Integer stationB);
    float getX(String trainType,String seatType);
}

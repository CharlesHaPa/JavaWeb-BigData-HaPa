package com.sw1408.vo;

public class TrainSeatVo {
	private int trainId;
	private String  arrival;
	private String departure;
	private String seatType;
	private String trainType;
	private String trainName;
	@Override
	public String toString() {
		return "TrainSeatVo [trainId=" + trainId + ", arrival=" + arrival + ", departure=" + departure + ", seatType="
				+ seatType + ", trainType=" + trainType + "]";
	}
	public int getTrainId() {
		return trainId;
	}
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String gettrainType() {
		return trainType;
	}
	public void settrainType(String trainType) {
		this.trainType = trainType;
	}
	public TrainSeatVo(int trainId, String arrival, String departure, String seatType, String trainType,String trainName) {
		super();
		this.trainId = trainId;
		this.arrival = arrival;
		this.departure = departure;
		this.seatType = seatType;
		this.trainType = trainType;
		this.trainName=trainName;
	}
	public TrainSeatVo() {
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	
}

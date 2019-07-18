package com.sw1408.vo;

import java.util.Arrays;

import com.sw1408.po.Passenger;

public class TicketVo {
	private int trainId;
	private String departure;
	private String arrival;
	private String seatType;
	private Passenger passenger;
	private double price;
	private int clientId;
	private int insuranceId[];
	private double insurancePrice[];

	public TicketVo() {
	
	}
	public int getTrainId() {
		return trainId;
	}
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int[] getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(int[] insuranceId) {
		this.insuranceId = insuranceId;
	}
	public double[] getInsurancePrice() {
		return insurancePrice;
	}
	public void setInsurancePrice(double[] insurancePrice) {
		this.insurancePrice = insurancePrice;
	}
	public TicketVo(int trainId, String departure, String arrival, Passenger passenger, double price, int clientId,
			String seatType,int[] insuranceId, double[] insurancePrice) {
		super();
		this.trainId = trainId;
		this.departure = departure;
		this.arrival = arrival;
		this.passenger = passenger;
		this.price = price;
		this.clientId = clientId;
		this.seatType =seatType;
		this.insuranceId = insuranceId;
		this.insurancePrice = insurancePrice;
	}
	@Override
	public String toString() {
		return "TicketVo [trainId=" + trainId + ", departure=" + departure + ", arrival=" + arrival + ", passenger="
				+ passenger + ", price=" + price + ", clientId=" + clientId + ", insuranceId="
				+ Arrays.toString(insuranceId) + ", insurancePrice=" + Arrays.toString(insurancePrice) + "]";
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
}

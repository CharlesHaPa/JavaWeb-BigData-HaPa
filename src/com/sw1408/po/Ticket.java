package com.sw1408.po;

import net.sf.json.JSONObject;

public class Ticket {
	
	private Integer id;
    private Integer seatId;
    private Integer passengerId;
    private String type;
    private String status;
    private double price;
    private Integer departure;
    private Integer arrival;

    public Ticket(int seatId, int passengerId, String type, String status, double price, int departure, int arrival) {
		super();
		this.seatId = seatId;
		this.passengerId = passengerId;
		this.type = type;
		this.status = status;
		this.price = price;
		this.departure = departure;
		this.arrival = arrival;
	}
    
	public Ticket(){
		
	}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getDeparture() {
        return departure;
    }

    public void setDeparture(Integer departure) {
        this.departure = departure;
    }

    public Integer getArrival() {
        return arrival;
    }

    public void setArrival(Integer arrival) {
        this.arrival = arrival;
    }

    @Override
	public String toString() {
		return "Ticket [id=" + id + ", seatId=" + seatId + ", passengerId=" + passengerId + ", type=" + type
				+ ", status=" + status + ", price=" + price + ", departure=" + departure + ", arrival=" + arrival + "]";
	}
	
}

package com.sw1408.util;

public class AvailableSeat {
	

	private String type;
	private int seatCount;
	private String price;
	
	public AvailableSeat(){}
	public AvailableSeat(String type, int seatCount, String price) {
		super();
		this.type = type;
		this.seatCount = seatCount;
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

    
	public void setPrice(String price) {
		this.price=price;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	

	@Override
	public String toString() {
		return "AvailableSeat [type=" + type + ", seatCount=" + seatCount + ", price=" + price + "]";
	}

}

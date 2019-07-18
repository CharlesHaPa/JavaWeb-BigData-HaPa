package com.sw1408.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * Created by DLETC on 2017/7/5.
 */
public class ItemDetail extends Filterable{
    private Integer orderId;
    private Integer clientId;
    private String orderStatus;
    private Integer ticketId;
    private String seatInfo;
    private String passengerName;
    private String passengerCardId;
    private String ticketType;
    private String ticketStatus;
    private double price;
    private String departure;
    private String arrival;
    private Date departTime;
    private Date createTime;
    private String trainName;
    private double rate;
    private String status;

    public String getStatus() {
		this.setStatus();
		return status;
	}

	public void setStatus() {
		if("退票".equals(this.getOrderStatus())){
			status = "returned";
		}
		else{
			if(departTime.after(new Date())){
				status = "notTravel";
			}
			else{
				status = "traveled";
			}
		}
	}

	public double getRate() {
    	this.setRate();
		return rate;
	}
    
	public void setRate() {
		long hour = (departTime.getTime() - new Date().getTime() )/(1000*60*60);
		if(hour>=15*24){
			this.rate = 0;
		}
		else if (hour>=48) {
			this.rate = 0.05;
		}
		else if(hour>=24){
			this.rate = 0.1;
		}
		else {
			this.rate = 0.2;
		}	
	}

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerCardId() {
        return passengerCardId;
    }

    public void setPassengerCardId(String passengerCardId) {
        this.passengerCardId = passengerCardId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }
    
    public Date getCreateTime() {
    	return createTime;
    }
    
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
    
    @Override
    public boolean matches(String ruleName, String value) {
    	if (null == value) {
    		throw new IllegalArgumentException("rule value cannot be null");
    	}
    	if ("orderStatus".equals(ruleName)) {
    		return this.orderStatus.equals(value);
    	} else if ("passengerName".equals(ruleName)) {
    		return this.passengerName.equals(value);
    	}else if ("startDepartTime".equals(ruleName)) {
    		Date date;
    		try {
    			if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
    				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
    			} else if (value.matches("\\d{4}-\\d{2}-\\d{2}")) {
    				date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
    			} else {
    				throw new IllegalArgumentException("wrong date format: " + value);
    			}
    			return this.getDepartTime().after(date);
    		} catch (ParseException e) {
				e.printStackTrace();
			}
    	}else if ("endDepartTime".equals(ruleName)) {
    		Date date;
    		try {
    			if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
    				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
    			} else if (value.matches("\\d{4}-\\d{2}-\\d{2}")) {
    				date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
    			} else {
    				throw new IllegalArgumentException("wrong date format: " + value);
    			}
				return this.getDepartTime().before(date);
    		} catch (ParseException e) {
				e.printStackTrace();
			}
    	}else if ("ticketStatus".equals(ruleName)) {
    		return this.ticketStatus.equals(value);
    	}
    	throw new IllegalArgumentException("Invalid rule name:" + ruleName);
    }
}
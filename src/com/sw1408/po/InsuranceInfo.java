package com.sw1408.po;

import java.sql.Date;

public class InsuranceInfo {
	int id;
	Date createDate;
	String type;
	String companyName;
	String description;
	float price;
	
	public InsuranceInfo() {
		super();
	}

	public InsuranceInfo(int id, Date createDate, String type, String companyName, String description,
			float price) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.type = type;
		this.companyName = companyName;
		this.description = description;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}

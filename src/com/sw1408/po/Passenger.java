package com.sw1408.po;

import net.sf.json.JSONObject;

public class Passenger {

	private int id;
	private String name;
	private String cardId;
	private int gender;
	private int isStudent;

	// Empty constructor
	public Passenger() {
	}

	/*// Constructor with name(String), cardId(String), gender(Integer) and
	// isStudent(Integer)
	public Passenger(Integer id, String name, String cardId, Integer gender, Integer isStudent) {
		super();
		this.id = id;
		this.name = name;
		this.cardId = cardId;
		this.gender = gender;
		this.isStudent = isStudent;
	}*/

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(int isStudent) {
		this.isStudent = isStudent;
	}

}

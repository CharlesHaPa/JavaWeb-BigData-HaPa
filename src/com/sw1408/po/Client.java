package com.sw1408.po;

import net.sf.json.JSONObject;

import java.sql.Date;

import com.sw1408.util.JsonDateValueProcessor;

public class Client {
	
    private Integer id;
    private String userName;
    private String psw;
    private String cardId;
    private Integer gender;
    private String address;
    private Date birth;
    private String email;
    private String telephone;
    public Client() {
    }

    public Client(String userName, String psw, String cardId, Integer gender, String address, Date birth, String email,
                  String telephone) {
        super();
        this.userName = userName;
        this.psw = psw;
        this.cardId = cardId;
        this.gender = gender;
        this.address = address;
        this.birth = birth;
        this.email = email;
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this,JsonDateValueProcessor.getJsonConfig()).toString();
    }

}

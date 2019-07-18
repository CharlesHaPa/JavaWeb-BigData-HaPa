package com.sw1408.po;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

import net.sf.json.JSONObject;

public class Seat {
    private Integer id;
    private String row_num;
    private String col_num;
    private String carriage;
    private Integer trainId;
    private String usable;
    private String type;

    public Seat(Integer id, String row_num, String col_num, String carriage, Integer trainId, String usable, String type) {
        super();
        this.id = id;
        this.row_num = row_num;
        this.col_num = col_num;
        this.carriage = carriage;
        this.trainId = trainId;
        this.usable = usable;
        this.type = type;
    }

    public Seat() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public String getCol_num() {
        return col_num;
    }

    public void setCol_num(String col_num) {
        this.col_num = col_num;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getUsable() {
        return usable;
    }

    public void setUsable(String usable) {
        this.usable = usable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
    public String seatInfo(){
    	return carriage+" 车 "+row_num+" 排  "+col_num+" 列 ";
    }
}

package com.sw1408.po;

import net.sf.json.JSONObject;

/**
 * Created by DLETC on 2017/6/30.
 */
public class Train {
    private Integer id;
    private String type;
    private String trainName;
    private String allPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getAllPath() {
        return allPath;
    }

    public void setAllPath(String allPath) {
        this.allPath = allPath;
    }

    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }

}

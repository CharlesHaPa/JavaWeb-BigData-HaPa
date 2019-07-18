package com.sw1408.po;

import net.sf.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by DLETC on 2017/7/3.
 */
public class Schedule {
    private Integer id;
    private Integer trainId;
    private Integer stationId;
    private Timestamp arriveTime;
    private Timestamp departTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Timestamp getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Timestamp arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Timestamp getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    @Override
    public String toString() {
        JSONObject map = new JSONObject();
        map.put("id", id);
        map.put("trainID", trainId);
        map.put("stationId", stationId);
        map.put("arriveTime", arriveTime.toString());
        map.put("departTime", departTime.toString());
        return map.toString();
    }
}

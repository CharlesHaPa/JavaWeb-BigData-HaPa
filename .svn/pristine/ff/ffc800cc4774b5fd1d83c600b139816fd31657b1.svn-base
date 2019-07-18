package com.sw1408.service;

import com.sw1408.po.Schedule;
import com.sw1408.po.Station;
import com.sw1408.po.Train;

import net.sf.json.JSONObject;

import java.sql.Date;
import java.util.List;

/**
 * Created by DLETC on 2017/7/3.
 */
public interface TrainService {
    /**
     *
     * @param trainId
     * @return
     * @author dkq
     */

    List<Station> allStations(int trainId);

    JSONObject queryTrainDetailByTrainNameAndStaionIdAndDepartDate(String trainName, int stationId, Date departDate);

    JSONObject queryTrainDetail(int trainId);

    List<JSONObject> queryTrainDetailOfToday();

    /**
     *
     * @param train
     * @param carriageQuantity
     * @param schedule
     */
    void addTrain(Train train, int carriageQuantity, Schedule schedule);
}

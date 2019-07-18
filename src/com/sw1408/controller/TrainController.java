package com.sw1408.controller;

import com.sw1408.service.TrainService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by DLETC on 2017/7/4.
 */
@Controller
public class TrainController {
    @Autowired
    private TrainService trainService;

    /**
     *
     * @param trainName
     * @param stationId
     * @param departDate
     * @return
     * @author dkq
     */
  //test url http://localhost:8080/train/queryTrainDetail?trainName=G1001&stationId=1&departDate=2017-7-1
    @RequestMapping(value = "/train/getTrains", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryTrainDetail() {
       /* ModelAndView mav = new ModelAndView();
        mav.addObject("trainDetailList", trainService.queryTrainDetailOfToday());
        mav.setViewName("/main/index");
        return mav;*/
    	JSONObject jsonObject = new JSONObject();
    	List<JSONObject> details = trainService.queryTrainDetailOfToday();
    	jsonObject.put("trainDetails", details);
    	return jsonObject.toString();
    }
}

package com.sw1408.mapper;

import com.sw1408.po.Schedule;

import java.util.List;

/**
 * Created by DLETC on 2017/7/3.
 */
public interface ScheduleMapper {
    /**
     *
     * @param trainId
     * @return
     */
    List<Schedule> selectScheduleByTrainId(int trainId);

    /**
     *
     * @param schedule
     * @return
     */
    Schedule selectScheduleByTrainIdAndStationId(int trainId, int stationId);

    /**
     *
     * @param schedule
     * @return
     */
    int addSchedule(Schedule schedule);
    
    List<Schedule> selectScheduleByDepartDate(java.sql.Date departDate);

    List<Schedule> selectScheduleByDepartDateRange(java.sql.Date startDate, java.sql.Date endDate);
}
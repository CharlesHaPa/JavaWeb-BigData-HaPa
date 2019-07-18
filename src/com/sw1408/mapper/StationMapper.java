package com.sw1408.mapper;

import com.sw1408.po.Station;

/**
 * Created by DLETC on 2017/7/3.
 */
public interface StationMapper {
    /**
     *
     * @param id
     * @return
     */
    Station selectStationById(int id);

    /**
     *
     * @param name
     * @return
     */
    Station selectStationByName(String name);
}

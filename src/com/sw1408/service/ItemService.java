package com.sw1408.service;

import com.sw1408.po.Item;
import com.sw1408.vo.ItemDetail;

import java.util.List;

/**
 * Created by DLETC on 2017/7/4.
 */
public interface ItemService {
    /**
     * \
     * @param id
     * @return
     * @author dkq
     */
    ItemDetail getItemDetail(int id);

    ItemDetail getItemDetail(Item item);

    List<ItemDetail> queryItemDetailByClientId(int clientId);

    List<ItemDetail> queryItemDetailByStatusAndCreateDateRange(String status, java.sql.Date startDate, java.sql.Date endDate);

    List<ItemDetail> queryItemDetailByPassengerName(String passengerName);

    List<ItemDetail> queryItemDetailByClientIdAndStatusAndCreateDateRange(int clientId, String status, java.sql.Date startDate, java.sql.Date endDate);
 
    List<ItemDetail> queryItemDetailByClientIdAndDepartDateRange(int clientid, java.sql.Date startDate, java.sql.Date endDate);
}

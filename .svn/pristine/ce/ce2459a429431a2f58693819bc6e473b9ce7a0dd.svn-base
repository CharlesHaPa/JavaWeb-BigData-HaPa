package com.sw1408.mapper;

import com.sw1408.po.Item;
import java.util.List;

/**
 * Created by DLETC on 2017/7/4.
 */
public interface ItemMapper {
    Item selectItemById(int id);
    Item selectItemByItemId(int id); 
	int updateItemByItemId(int id,String status);
	List<Item> selectItemByTicketId(int ticketId);

	List<Item> selectItemByClientId(int clientId);

	List<Item> selectItemByClientIdAndCreateDateAndStaus(int clientId, java.sql.Date createDate, String status);

	List<Item> selectItemsByStatusAndCreateDateRange(String status, java.sql.Date startDate, java.sql.Date endDate);

    List<Item> selectItemByClientIdAndStatusAndCreateDateRange(int clientId, String status, java.sql.Date startDate, java.sql.Date endDate);

}

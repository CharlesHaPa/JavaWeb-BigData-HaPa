package com.sw1408.po;

import java.util.Date;
import net.sf.json.JSONObject;

/**
 * Created by DLETC on 2017/7/4.
 */
public class Item {
    private Integer id;
    private Integer clientId;
    private Integer ticketId;
    private String status;
    private Date createDate;
    private Date payDate;
    private double totalFee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        JSONObject map = new JSONObject();
        map.put("id", id);
        map.put("clientId", clientId);
        map.put("ticketId", ticketId);
        map.put("status", status);
        map.put("createDate", createDate.toString());
        map.put("payDate", createDate.toString());
        map.put("toFee", totalFee);
        return map.toString();
    }
}

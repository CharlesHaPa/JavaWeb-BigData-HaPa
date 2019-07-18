package com.sw1408.service;
import java.sql.ResultSet;
import java.util.List;

import java.util.List;

public interface AdminService {
	String getSeatInfo(String departure,String arrival,String date);
	String getMaleAnalysis(String year) ;
	String getFamaleAnalysis(String year) ;
	List<String> countItemByHour(String year);
	ResultSet showTop5TicketsNumGroupByCity(String year);
	int showAllTicketsNum(String year) throws Exception;
	public boolean addTables(String table,String dir,String dbName);
	public List<String> getTables(String db,String dbName);
	public void delTable(String table,String db);
	public boolean runCmd(String sql,String table,String db,String dir);
	public List<String> getDbs();
	public List<String> getStructure(String db,String table);
	public int adminLogin(String userName,String psw);
}

package com.sw1408.controller;

import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sw1408.util.RunCmdUtil;
import com.sw1408.util.SqoopParams;
import com.sw1408.exception.MyTestException;;
@Controller
public class MapDataController {
	
	private SqoopParams sqoopParams = new SqoopParams();
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/admin/map_manage")
	public ModelAndView page_redirect() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/map_manage");
		return mav;
	}
	
	@RequestMapping("/admin/map/testConnection")
	public @ResponseBody String testConnection(@ModelAttribute SqoopParams sqoopParams) throws ClassNotFoundException {
		System.out.println(sqoopParams.getJdbcUrl());
		if (sqoopParams.getDatabaseType().equals("oracle")) {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
		} else if (sqoopParams.getDatabaseType().equals("mysql")) {
			Class.forName("com.mysql.jdbc.Driver"); 
		}
		
		String url = sqoopParams.getJdbcUrl();
		String user = sqoopParams.getUserName(); 
		String password = sqoopParams.getPassword();
		try {
			Connection conn= DriverManager.getConnection(url,user,password);
			return "{\"status\": \"success\"}";
		} catch (Exception e) {
			return "{\"status\": \"failed\"}";
		}
	}
	
	@RequestMapping("/admin/map/transferToHive")
	public @ResponseBody String transferToHive(@ModelAttribute SqoopParams sqoopParams) {
		String cmd = " sqoop import --connect " +  sqoopParams.getJdbcUrl() + 
				" --user " + sqoopParams.getUserName() + 
				" --password " + sqoopParams.getPassword() + 
				" --query '" + sqoopParams.getImportView() + 
					(sqoopParams.getCondition().equals("") ? "" : (" where " + sqoopParams.getCondition() + " and \\$CONDITIONS")) +
				" '" +
				" --hive-import --hive-database " + sqoopParams.getHiveDatabase() + 
				" --hive-table " + sqoopParams.getHiveTable();

		System.out.println(cmd);
		try {
			RunCmdUtil cmdUtil = new RunCmdUtil();
			cmdUtil.executeCommand(cmd);
			return "{\"status\": \"success\"}";
		} catch (Exception e) {
			return "{\"status\": \"failed\"}";
		}
		
	}
	
	
}

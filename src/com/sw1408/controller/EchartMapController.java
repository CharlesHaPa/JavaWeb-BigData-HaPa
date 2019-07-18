package com.sw1408.controller;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sw1408.util.HiveUtil;


@Controller
public class EchartMapController {
	private static Color[] orderedColor = {
			new Color(138,43,226),//    blue violet
			new Color(0,255,0),//    lime
			new Color(124,252,0),//    lawn green
			new Color(173,255,47),//    green yellow
			new Color(255,255,0),//    yellow
			new Color(255,215,0),//    gold
			new Color(255,165,0),//    orange
			new Color(255,69,0),//    orange red
			new Color(255,0,0),//    red
	};
	
    @RequestMapping("/map/railPressure")
    public ModelAndView railPressureMap() {
    	ModelAndView mav = new ModelAndView();
    	List<List<String>> items;
		try {
			items = getData("railPath");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	mav.addObject("items", items);
    	List<String> dataNames = new ArrayList<>();
    	for (int i = 0; i < items.size(); i++) {
    		dataNames.add("data"+i);
    	}
    	mav.addObject("dataNames", dataNames);
    	mav.setViewName("admin/map");
    	return mav;
    }
    
    @RequestMapping("/map/cityPressure")
    public ModelAndView cityPressureMap() {
    	ModelAndView mav = new ModelAndView();
    	List<List<String>> items;
		try {
			items = getData("travel");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	mav.addObject("items", items);
    	List<String> dataNames = new ArrayList<>();
    	for (int i = 0; i < items.size(); i++) {
    		dataNames.add("data"+i);
    	}
    	mav.addObject("dataNames", dataNames);
    	mav.setViewName("admin/map");
    	return mav;
    }

    public List<List<String>> getData(String tableName) throws SQLException {
    	String s = "";
    	HiveUtil hiveUtil = new HiveUtil("hapa");
    	Map<String, String> stationMap = new HashMap<>();
    	ResultSet result = hiveUtil.executeQuery("select * from stations");
    	
    	while (result.next()) {
    		stationMap.put(result.getString(1), result.getString(2));
    	}
    	
    	//TO DO: change to select path, count(path) from travel group by path

    	ResultSet paths = hiveUtil.executeQuery("select path, count(path) from " + tableName + " group by path");
    	
    	List<Path> pathList = new ArrayList<>();
    	while (paths.next()) {
    		String[] split = paths.getString(1).split("-");
    		String from = stationMap.get(split[0]);
    		String to = stationMap.get(split[1]);
    		int count = paths.getInt(2);
    		pathList.add(new Path(from, to, count));
    		System.out.println(from + to + count);
    	}
    	Collections.sort(pathList);
		return convertPathDataToJsonData(pathList);
    }
    
    private List<List<String>> convertPathDataToJsonData(List<Path> paths) {
    	List<String> result = new ArrayList<>();
    	List<List<String>> results = new ArrayList<>();
    	Map<Integer, List<String>> resultMap = new HashMap<>();
    	for (int i = 0; i < paths.size(); i++) {
    		Path path = paths.get(i);
    		int width = 2;
    		//TO DO: 将count映射到颜色
    		int colorIndex = i * orderedColor.length / paths.size();
    		Color c =  orderedColor[colorIndex];
    		String color = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());;
    		String s= "\n[{name: '" + path.from + "'}," + "{name: '" + path.to + "'}," + "{color: '" + color + "', width: " + width + "}]";
    		if (!resultMap.containsKey(colorIndex)) {
    			resultMap.put(colorIndex, new ArrayList<String>());
    		}
    		resultMap.get(colorIndex).add(s);
    	}
    	results.addAll(resultMap.values());
    	return results;
    }
     
    private class Path implements Comparable<Path>{
    	String from;
    	String to;
    	int count;
    	
    	Path(String from , String to, int count) {
    		this.from = from;
    		this.to= to;
    		this.count = count;
    	}
    	@Override
		public String toString() {
    		return from + " " + to + " " + count;
    	}
		@Override
		public int compareTo(Path o) {
			// TODO Auto-generated method stub
			return this.count - o.count;
		}
    }
}

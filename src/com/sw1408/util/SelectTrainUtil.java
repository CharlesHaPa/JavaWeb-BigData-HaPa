package com.sw1408.util;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import com.sw1408.vo.AvailableTrain;


public class SelectTrainUtil {

	public LinkedList<AvailableTrain> selectTrain(LinkedList<AvailableTrain> trains,String departDate){
		
		//获取当前时间
		java.util.Date d = new java.util.Date();  	 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateNowStr = sdf.format(d);  
	    //获取当前月日
	    String[] currentDates = dateNowStr.split("-");
	    int currentMonth =Integer.parseInt(currentDates[1]);
	    int currentDay =Integer.parseInt(currentDates[2]);
		//获取查询月日
		String[] conditionDates = departDate.split("-");
		int conditionMonth = Integer.parseInt(conditionDates[1]);
		int conditionDay = Integer.parseInt(conditionDates[2]);
		System.out.println("condition:"+conditionDay);
		System.out.println("condition:"+conditionMonth);
		//筛去当天已出发车次
		if(currentMonth == conditionMonth && currentDay == conditionDay ){
			for (int i=0 ; i<trains.size() ; i++) {
				String trainDates[] = trains.get(i).getStartDate().split(":");
				//获取火车出发小时分
				int trainHour = Integer.parseInt(trainDates[0]);
				int trainMinutes = Integer.parseInt(trainDates[1]);
				System.out.print("trainName:"+trains.get(i).getTrainName()+" ");
				System.out.print("trainHour:"+trainHour+" ");
				System.out.print("trainMinutes:"+trainMinutes+" ");
				int currentHour = d.getHours();
				int currentMinute = d.getMinutes();
				System.out.println("currentHour:"+currentHour);
				System.out.println("currentMinutes:"+currentMinute);
				if(currentHour > trainHour){
					trains.remove(trains.get(i));
					i--;
				}
				else if(currentHour == trainHour && currentMinute > trainMinutes){
					trains.remove(trains.get(i));
					i--;
				}
			}
		}
		return trains;
	}
}

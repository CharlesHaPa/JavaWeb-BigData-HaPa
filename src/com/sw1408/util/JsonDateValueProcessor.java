package com.sw1408.util;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/** * JSON 日期格式处理（java转化为JSON） */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private static JsonDateValueProcessor dateValueProcessor;
	/**
	 * datePattern
	 */
	private String datePattern = "yyyy-MM-dd";

	public JsonDateValueProcessor(){
	}
	public static JsonDateValueProcessor getInstance(){
		if(dateValueProcessor==null){
			dateValueProcessor = new JsonDateValueProcessor();
		}
		return dateValueProcessor;
	}
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}
	// 用于格式化date类型的值
	private Object process(Object value) {
		try {
			if (value instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern,
						Locale.CHINESE);
				return sdf.format((Date) value);
			}
			return value == null ? "" : value.toString();
		} catch (Exception e) {
			return "";
		}
	}
	public static JsonConfig getJsonConfig(){
		JsonConfig jcfg = new JsonConfig();
		jcfg.registerJsonValueProcessor(Date.class,JsonDateValueProcessor.getInstance());
		return jcfg;
	}
}
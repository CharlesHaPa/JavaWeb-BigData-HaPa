package com.sw1408.vo;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Filter<T extends Filterable> {
	
	private List<T> dataList;
	
	public Filter(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public Filter<T> filter(String ruleName, String value) {
        for (int i = 0; i < dataList.size();) {
            if (!dataList.get(i).matches(ruleName, value)) {
                dataList.remove(i);
                continue;
            }
            i++;
        }
        return this;
    }
	
	public List<T> result() {
		return dataList;
	}
}

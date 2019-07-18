package com.sw1408.mapper;

import java.util.List;

import com.sw1408.po.InsuranceInfo;

public interface InsuranceInfoMapper {
	List<InsuranceInfo> selectInsuranceInfo(int id);
}

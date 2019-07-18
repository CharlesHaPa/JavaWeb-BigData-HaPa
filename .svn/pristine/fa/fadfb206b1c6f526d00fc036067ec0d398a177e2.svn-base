package com.sw1408.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sw1408.mapper.InsuranceInfoMapper;
import com.sw1408.po.InsuranceInfo;
import com.sw1408.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService{
	@Resource
	InsuranceInfoMapper insuranceInfoMapper;
	
	@Override
	public List<InsuranceInfo> showInsuranceInfo(int id) {
		List<InsuranceInfo> list = insuranceInfoMapper.selectInsuranceInfo(id);
		return list;
	}
	
}

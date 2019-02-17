package com.cav.spring.service.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cav.spring.service.bank.cache.FundCache;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.Funds;

@Service
public class FundServiceImpl implements FundService {
	
	
	@Autowired
	FundCache fundCache;

	@Override
	public void updateFunds(Funds funds) {
		fundCache.updateFunds(funds);
	}

	public Funds listFunds(List<FundId> fundIds) {
		return fundCache.listFunds(fundIds);
	}

	@Override
	public void removeFunds(List<FundId> fundIds) {
		fundCache.removeFunds(fundIds);
	}
	
}

package com.cav.spring.service.bank.service;

import java.util.List;

import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.Funds;

public interface FundService {

	public void updateFunds(Funds fund);
	public Funds listFunds(List <FundId> fundIds);
	public void removeFunds(List <FundId> fundIds);
}

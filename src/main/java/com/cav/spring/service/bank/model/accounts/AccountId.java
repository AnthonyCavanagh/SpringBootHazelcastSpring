package com.cav.spring.service.bank.model.accounts;

import java.util.List;

import com.cav.spring.service.bank.model.funds.FundId;

public class AccountId {

	private Long id;
	private List <FundId> fundIds;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<FundId> getFundIds() {
		return fundIds;
	}

	public void setFundIds(List<FundId> fundIds) {
		this.fundIds = fundIds;
	}

}

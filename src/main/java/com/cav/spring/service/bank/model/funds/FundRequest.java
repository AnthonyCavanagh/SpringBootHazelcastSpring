package com.cav.spring.service.bank.model.funds;

import java.util.ArrayList;
import java.util.List;

public class FundRequest {
	private List <FundId> fundIds = new ArrayList<FundId>();

	public List<FundId> getFundIds() {
		return fundIds;
	}

	public void setFundIds(List<FundId> fundIds) {
		this.fundIds = fundIds;
	}

	@Override
	public String toString() {
		return "FundRequest [fundIds=" + fundIds + "]";
	}
}

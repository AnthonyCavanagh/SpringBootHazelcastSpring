package com.cav.spring.service.bank.model.accounts;

import java.util.ArrayList;
import java.util.List;

public class AccountRequest {
	private List <AccountId> accountIds = new ArrayList<AccountId>();

	public List<AccountId> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<AccountId> accountIds) {
		this.accountIds = accountIds;
	}
}

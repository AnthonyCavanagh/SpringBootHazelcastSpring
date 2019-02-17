package com.cav.spring.service.bank.model.banks;

import java.util.ArrayList;
import java.util.List;

public class BankRequest {
	
	private List <BankId> bankIds = new ArrayList<BankId>();
	private boolean accounts = true;
	private boolean funds = true;

	public List<BankId> getBankIds() {
		return bankIds;
	}

	public void setBankIds(List<BankId> bankIds) {
		this.bankIds = bankIds;
	}

	public boolean isAccounts() {
		return accounts;
	}

	public void setAccounts(boolean accounts) {
		this.accounts = accounts;
	}

	public boolean isFunds() {
		return funds;
	}

	public void setFunds(boolean funds) {
		this.funds = funds;
	}
}

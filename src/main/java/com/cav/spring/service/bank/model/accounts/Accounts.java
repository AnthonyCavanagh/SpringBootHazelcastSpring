package com.cav.spring.service.bank.model.accounts;

import java.util.ArrayList;
import java.util.List;

public class Accounts {
	
	List <AccountPOJO> accountsList = new ArrayList <AccountPOJO>();

	public List<AccountPOJO> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(List<AccountPOJO> accountsList) {
		this.accountsList = accountsList;
	}

}

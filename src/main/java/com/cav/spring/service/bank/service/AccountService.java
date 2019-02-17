package com.cav.spring.service.bank.service;

import java.util.List;

import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.accounts.Accounts;


public interface AccountService {
	
	public void updateAccounts(Accounts accounts);
	public Accounts listAccounts(List<AccountId> accountIds);
	public void removeAccounts(List<AccountId> accountIds);
	public void removeFunds(List<AccountId> accountIds);

}

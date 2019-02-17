package com.cav.spring.service.bank.repository.account;

import java.util.List;

import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.accounts.AccountPOJO;
import com.cav.spring.service.bank.model.accounts.Accounts;

public interface AccountRepository {
	public void updateAccounts(List <AccountPOJO> accounts);
	public Accounts listAccounts(List<AccountId> accountIds);
	public void removeAccounts(List<AccountId> accountIds);
	public void removeFunds(List<AccountId> accountIds);
}

package com.cav.spring.service.bank.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cav.spring.service.bank.cache.FundCache;
import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.repository.account.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;
	
	@Autowired
	FundCache fundCache;

	@Override
	/**
	 * In this version, to not duplicate fund data in the cache, the assumption is that
	 * most requests will be for funds only so will only update the
	 * fund cache with funds.
	 */
	public void updateAccounts(Accounts accounts) {
		fundCache.updateFundsAccount(accounts.getAccountsList());
	}

	@Override
	public Accounts listAccounts(List<AccountId> accountIds) {
		return repository.listAccounts(accountIds);	
	}

	@Override
	public void removeAccounts(List<AccountId> accountIds) {
		repository.removeAccounts(accountIds);
	}

	@Override
	public void removeFunds(List<AccountId> accountIds) {
		repository.removeFunds(accountIds);
	}
}

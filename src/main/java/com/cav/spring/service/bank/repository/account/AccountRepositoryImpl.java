package com.cav.spring.service.bank.repository.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cav.spring.service.bank.entity.Account;
import com.cav.spring.service.bank.entity.Fund;
import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.accounts.AccountPOJO;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.repository.fund.FundJpaRepository;
import com.cav.spring.service.bank.service.MappEntitiesAbstract;

@Service
public class AccountRepositoryImpl extends MappEntitiesAbstract  implements AccountRepository {
	
	@Autowired
	AccountJPARepository accountRepository;
	
	@Autowired
	FundJpaRepository fundRepository;

	@Override
	public void updateAccounts(List <AccountPOJO> accounts) {
		List<Account> entities = new ArrayList<Account>();;
		for(AccountPOJO account: accounts){
			Account accountEntity = accountRepository.findOne(account.getAccountid());
			if(accountEntity != null){
				entities.add(updateAccountEntity(account, accountEntity));
			} else {
				// Errors Account Id dont exist
			}
		}
		accountRepository.save(entities);
	}

	@Override
	public Accounts listAccounts(List<AccountId> accountIds) {
		List<Account> accountList = new ArrayList<Account>();
		for(AccountId accountId : accountIds){
			Account accountEntity = accountRepository.findOne(accountId.getId());
			if(accountEntity != null){
				accountList.add(accountEntity);
			}
		}
		return mapAccounts(accountList);
	}

	@Override
	public void removeAccounts(List<AccountId> accountIds) {
		for(AccountId accountId: accountIds){
			try {
				accountRepository.delete(accountId.getId());
			} catch (EmptyResultDataAccessException e) {
				//Do nothing
			}
		}		
	}

	@Override
	public void removeFunds(List<AccountId> accountIds) {
		for(AccountId accountId : accountIds){
			Account accountEntity = accountRepository.findOne(accountId.getId());
			if(accountEntity != null){
				for(FundId fundId : accountId.getFundIds()){
					accountEntity.removeFunds(fundRepository.findOne(fundId.getId()));
				}
			}
		}
	}
}

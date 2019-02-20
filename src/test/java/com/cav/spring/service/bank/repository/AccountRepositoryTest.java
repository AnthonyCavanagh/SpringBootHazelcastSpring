package com.cav.spring.service.bank.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.accounts.AccountPOJO;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.model.banks.BankId;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.banks.BankRequest;
import com.cav.spring.service.bank.model.banks.Banks;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.FundPOJO;
import com.cav.spring.service.bank.model.funds.Funds;

public class AccountRepositoryTest extends AbstractRepositoryTest {
	
	@Before
	public void setUp(){
		super.setUp();
	}
	
	@After
	public void cleanUp(){
		super.cleanUp();
	}
	
	@Test
	public void testCreateAccounts(){
		List<BankId> bankIds = createBankIds(DEFAULT_BANK_ID);
		Banks banks = bankRepository.listBanks(bankIds);
		BankPOJO bank = banks.getBankList().iterator().next();
		assertEquals(DEFAULT_BANK_ID,bank.getBankId());
		Iterator<AccountPOJO> accountIter = bank.getAccounts().getAccountsList().iterator();
		assertEquals(DEFAULT_ACCOUNT_ID, accountIter.next().getAccountid());
	}
	
	
	@Test
	public void testCreateUpdateAccountWithExistingAndNewFund(){
		List<BankPOJO> bankList = createNewAccount(DEFAULT_BANK_ID,NEW_ACCOUNT_ID);
		Banks banks = new Banks();
		banks.setBankList(bankList);
		bankRepository.updateBanks(banks.getBankList());
		
		Accounts accounts = createAccountFund(NEW_ACCOUNT_ID, DEFAULT_FUND_ID);
		accountRepository.updateAccounts(accounts.getAccountsList());
		
		accounts = accountRepository.listAccounts(createAccountsIds(NEW_ACCOUNT_ID));
		AccountPOJO account = accounts.getAccountsList().iterator().next();
		List<FundPOJO> accountList = account.getFunds().getFundList();
		
		assertEquals(2, accountList.size());
		
		List<AccountId> accountIds = createAccountsIds(NEW_ACCOUNT_ID);
		accountRepository.removeAccounts(accountIds);
		accounts = accountRepository.listAccounts(accountIds);
		assertTrue(accounts.getAccountsList().isEmpty());
	}
	
	@Test
	public void removeFundFromAccount(){
		accountRepository.removeFunds(createAccountsFundsIds(DEFAULT_ACCOUNT_ID,DEFAULT_FUND_ID));
		Accounts accounts = accountRepository.listAccounts(createAccountsIds(DEFAULT_ACCOUNT_ID));
		AccountPOJO account = accounts.getAccountsList().iterator().next();
		assertEquals(DEFAULT_ACCOUNT_ID,account.getAccountid());
		assertTrue(account.getFunds().getFundList().isEmpty());
	}

}

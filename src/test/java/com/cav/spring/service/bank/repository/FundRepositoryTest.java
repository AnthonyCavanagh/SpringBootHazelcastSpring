package com.cav.spring.service.bank.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.accounts.AccountPOJO;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.banks.Banks;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.FundPOJO;
import com.cav.spring.service.bank.model.funds.Funds;

public class FundRepositoryTest extends AbstractRepositoryTest {
	
	//Only updates finds and removes  allowed on fund Repository
	// Finds and removes already tested.
	@Before
	public void setUp(){
		super.setUp();
	}
	
	@After
	public void cleanUp(){
		super.cleanUp();
	}
	@Test
	public void updateFundTest(){
		Funds funds = updateFund(DEFAULT_FUND_ID, "New Fund Name");
		fundRepository.updateFunds(funds.getFundList());
		List<FundPOJO> fundList = fundRepository.listFunds(createIds(DEFAULT_FUND_ID));
		assertEquals(DEFAULT_FUND_ID,fundList .iterator().next().getFundId());
		assertEquals("New Fund Name",fundList .iterator().next().getFundName());
	}
	
	@Test
	public void deleteFundFromAccount(){
		// Create a new account with a new fund
		List<BankPOJO> bankList = createNewAccount(DEFAULT_BANK_ID,NEW_ACCOUNT_ID);
		Banks banks = new Banks();
		banks.setBankList(bankList);
		bankRepository.updateBanks(banks.getBankList());
		
		//Add a existing fund
		Accounts accounts = createAccountFund(NEW_ACCOUNT_ID, DEFAULT_FUND_ID);
		accountRepository.updateAccounts(accounts.getAccountsList());
		
		// Get the new account
		accounts = accountRepository.listAccounts( createAccountsIds(NEW_ACCOUNT_ID));
		AccountPOJO account = accounts.getAccountsList().iterator().next();
		assertEquals(2, account.getFunds().getFundList().size());
		
		accountRepository.removeFunds(createAccountFundsIds(NEW_ACCOUNT_ID, DEFAULT_FUND_ID));
		accounts = accountRepository.listAccounts( createAccountsIds(NEW_ACCOUNT_ID));
		account = accounts.getAccountsList().iterator().next();
		assertEquals(1, account.getFunds().getFundList().size());
		
		List<FundPOJO> funds = fundRepository.listFunds(createIds(DEFAULT_FUND_ID));
		assertEquals(DEFAULT_FUND_ID, funds.iterator().next().getFundId());
		
	}

}

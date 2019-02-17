package com.cav.spring.service.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

public abstract class AbstractTest {
	
	protected static final long DEFAULT_BANK_ID = 123L;
	protected static final long DEFAULT_ACCOUNT_ID = 1234L;
	protected static final long NEW_ACCOUNT_ID = 1235L;
	protected static final long DEFAULT_FUND_ID = 12345L;
	protected static final long NEW_FUND_ID = 12346L;
	
	
	protected List<BankPOJO> createBankList() {
		List<BankPOJO> bankList = new ArrayList<BankPOJO>();
		BankPOJO bank = createBank(DEFAULT_BANK_ID);
		List<AccountPOJO> accountList = new ArrayList<AccountPOJO>();
		AccountPOJO account = createaccount(DEFAULT_ACCOUNT_ID);
		List<FundPOJO> fundList = new ArrayList<FundPOJO>();
		fundList.add(createFund(DEFAULT_FUND_ID));
		Funds funds = new Funds();
		funds.setFundList(fundList);
		account.setFunds(funds);
		accountList.add(account);
		Accounts accounts = new Accounts();
		accounts.setAccountsList(accountList);
		bank.setAccounts(accounts);
		bankList.add(bank);
		return bankList;
	}
	
	protected List<BankPOJO> createNewAccount(Long bankId, Long accountId) {
		List<BankPOJO> bankList = new ArrayList<BankPOJO>();
		BankPOJO bank = createBank(bankId);
		List<AccountPOJO> accountList = new ArrayList<AccountPOJO>();
		AccountPOJO account = createaccount(accountId);
		List<FundPOJO> fundList = new ArrayList<FundPOJO>();
		fundList.add(createFund(NEW_FUND_ID));
		Funds funds = new Funds();
		funds.setFundList(fundList);
		account.setFunds(funds);
		accountList.add(account);
		Accounts accounts = new Accounts();
		accounts.setAccountsList(accountList);
		bank.setAccounts(accounts);
		bankList.add(bank);
		return bankList;
	}

	protected BankPOJO createBank(Long bankId) {
		BankPOJO bank = new BankPOJO();
		bank.setBankId(bankId);
		bank.setBankCode("CAV101L0");
		bank.setBankName("Natwest");
		bank.setContactDetailsCode(12345);
		bank.setEffectiveDate(LocalDate.now());
		bank.setActive("Y");
		return bank;
	}
	
	protected Accounts createAccountFund(Long accountId, Long fundId){
		List<AccountPOJO> accountList = new ArrayList<AccountPOJO>();
		AccountPOJO account = createaccount(accountId);
		List<FundPOJO> fundList = new ArrayList<FundPOJO>();
		fundList.add(createFund(fundId));
		Funds funds = new Funds();
		funds.setFundList(fundList);
		account.setFunds(funds);
		accountList.add(account);
		Accounts accounts = new Accounts();
		accounts.setAccountsList(accountList);
		return accounts;
	}

	protected AccountPOJO createaccount(Long accountId) {
		AccountPOJO account = new AccountPOJO();
		account.setAccountid(accountId);
		account.setAccountName("BlackRock");
		account.setActive("Y");
		account.setEffectiveDate(LocalDate.now());
		return account;
	}

	protected Funds updateFund(Long fundId, String fundName){
		List<FundPOJO> fundList = new ArrayList<FundPOJO>();
		FundPOJO fund = createFund(fundId);
		fund.setFundName(fundName);
		fundList.add(fund);
		Funds funds = new Funds();
		funds.setFundList(fundList);
		return funds;
	}
	protected FundPOJO createFund(Long fundid) {
		FundPOJO fund = new FundPOJO();
		fund.setFundId(fundid);
		fund.setFundName("Imperial");
		fund.setBuy(new BigDecimal(25.00));
		fund.setSell(new BigDecimal(20.00));
		fund.setYield(5f);
		fund.setEffectiveDate(LocalDate.now());
		return fund;
	}
	
	BankPOJO createBank(int bankId, String bankName, String bankCode){
		BankPOJO bank = new BankPOJO();
		bank.setBankId(bankId);
		bank.setBankName(bankName);
		bank.setBankCode(bankCode);
		bank.setActive("Y");
		bank.setEffectiveDate(LocalDate.now());
		return bank;
	}
	
	protected AccountPOJO createAccount(long accountid, String accountName){
		AccountPOJO account = new AccountPOJO();
		account.setAccountid(accountid);
		account.setAccountName(accountName);
		account.setActive("Y");
		account.setEffectiveDate(LocalDate.now());
		return account;
	}
	
	protected List <BankId> createBankIds(Long id){
		List <BankId> ids = new ArrayList <BankId> ();
		BankId bankId = new BankId();
		bankId.setId(id);
		ids.add(bankId);
		return ids;
	}
	
	protected List <BankId> createBankAccountIds(Long bId, Long aId){
		List <BankId> ids = new ArrayList <BankId> ();
		BankId bankId = new BankId();
		bankId.setId(bId);
		bankId.setAccountIds(createAccountsIds(aId));
		ids.add(bankId);
		return ids;
	}
	
	protected AccountId createAccountId(Long id){
		List <AccountId> ids = new ArrayList <AccountId> ();
		AccountId accountId = new AccountId();
		accountId.setId(id);
		return accountId;
	}
	
	protected List <AccountId> createAccountsIds(Long id){
		List <AccountId> ids = new ArrayList <AccountId> ();
		AccountId accountId = new AccountId();
		accountId.setId(id);
		ids.add(accountId);
		return ids;
	}
	
	protected List <AccountId> createAccountsFundsIds(Long id, Long fId){
		List <AccountId> ids = new ArrayList <AccountId> ();
		AccountId accountId = new AccountId();
		accountId.setId(id);
		accountId.setFundIds(createFundsIds(fId));
		ids.add(accountId);
		return ids;
	}
	
	protected List <Long> createIds(Long id){
		List <Long> ids = new ArrayList <Long> ();
		ids.add(id);
		return ids;
	}
	
	protected List <FundId> createFundsIds(Long id){
		List <FundId> ids = new ArrayList <FundId> ();
		FundId fundId = new FundId();
		fundId.setId(id);
		ids.add(fundId);
		return ids;
	}
	
	protected List <AccountId> createAccountFundsIds(Long accId, Long fundId){
		List <AccountId> ids = new ArrayList <AccountId> ();
		AccountId accountId = new AccountId();
		accountId.setId(accId);
		accountId.setFundIds(createFundsIds(fundId));
		ids.add(accountId);
		return ids;
	}

}

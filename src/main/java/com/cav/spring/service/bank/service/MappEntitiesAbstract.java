package com.cav.spring.service.bank.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.cav.spring.service.bank.entity.Account;
import com.cav.spring.service.bank.entity.Bank;
import com.cav.spring.service.bank.entity.Fund;
import com.cav.spring.service.bank.model.accounts.AccountPOJO;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.banks.Banks;
import com.cav.spring.service.bank.model.funds.FundPOJO;
import com.cav.spring.service.bank.model.funds.Funds;

public abstract class MappEntitiesAbstract {

	/**
	 * Creates Bank Entitys
	 * @param b
	 * @return
	 */
	protected Bank createBankEntity(BankPOJO b) {
		Bank be = new Bank();
		be.setActive(b.getActive());
		be.setBankCode(b.getBankCode());
		be.setBankId(b.getBankId());
		be.setBankName(b.getBankName());
		be.setContactDetailsCode(b.getContactDetailsCode());
		be.setEffectiveDate(mappDateToEntity(b.getEffectiveDate()));
		List<Account> accounts = createAccountEntities(b.getAccounts());
		for (Account account : accounts) {
			be.setAccounts(account);
		}
		return be;
	}

	/**
	 * updates Bank Enties
	 * @param b
	 * @param be
	 * @return
	 */
	protected Bank updateBankEntity(BankPOJO b, Bank be) {
		if (b.getActive() != null) {
			be.setActive(b.getActive());
		}
		if (b.getBankCode() != null) {
			be.setBankCode(b.getBankCode());
		}
		if (b.getBankName() != null) {
			be.setBankName(b.getBankName());
		}
		if (b.getContactDetailsCode() != null) {
			be.setContactDetailsCode(b.getContactDetailsCode());
		}
		List<Account> accounts = createAccountEntities(b.getAccounts());
		for (Account account : accounts) {
			be.setAccounts(account);
		}
		Set<Account> alist = be.getAccounts();
		for(Account a : alist){
			Set<Fund> flist = a.getFunds();
			System.out.println(a.getAccountid());
			for(Fund f : flist){
				System.out.println("Fund "+f.getFundId());
			}
		}
		return be;
	}

	/**
	 * FindsBanks
	 * @param bankEnties
	 * @param accounts
	 * @param funds
	 * @return
	 */
	protected Banks mapdBanks(List<Bank> bankEnties) {
		Banks banks = new Banks();
		List<BankPOJO> bankList = bankEnties.stream().map(b -> mapBank(b)).collect(Collectors.toList());
		banks.setBankList(bankList);
		return banks;
	}

	/**
	 * Updates Account Entity
	 * @param account
	 * @param ae
	 * @return
	 */
	protected Account updateAccountEntity(AccountPOJO account, Account ae) {
		if(account.getAccountName() != null) {
			ae.setAccountName(account.getAccountName());
		}
		List<Fund> fundEntities = createFundEntities(account.getFunds());
		for (Fund fund : fundEntities) {
			ae.setFunds(fund);
		}
		return ae;
	}
	
	/**
	 *  Finds Accounts
	 * @param accountSet
	 * @param funds
	 * @return
	 */
	protected Accounts mapAccounts(Collection <Account> accountCollection) {
		Accounts accounts = new Accounts();
		List<AccountPOJO> accountList = accountCollection.stream().map(a -> mapAccount(a)).collect(Collectors.toList());
		accounts.setAccountsList(accountList);
		return accounts;
	}
	
	/**
	 * Updates a Fund
	 * @param fund
	 * @return
	 */
	protected Fund updateFundEntity(FundPOJO fund, Fund fe) {
		if(fund.getFundName() != null){
			fe.setFundName(fund.getFundName());
		}
		if(fund.getSell() != null){
			fe.setSell(fund.getSell());
		}
		if(fund.getBuy() != null){
			fe.setBuy(fund.getBuy());
		}
		if(fund.getYield() != null){
			fe.setYeild(fund.getYield());
		}
		return fe;
	}
	
	/**
	 * 
	 * @param fundSets
	 * @return
	 */
	protected Funds mapFunds(Collection<Fund> fundCollection) {
		Funds funds = new Funds();
		List<FundPOJO> fundList = fundCollection.stream().filter(Objects::nonNull).map(f -> mapFund(f)).collect(Collectors.toList());
		funds.setFundList(fundList);
		return funds;
	}
	
	/**
	 * 
	 * @param fundSets
	 * @return
	 */
	protected List<FundPOJO>  mapFundList(Collection<Fund> fundCollection) {
		Funds funds = new Funds();
		List<FundPOJO> fundList = fundCollection.stream().filter(Objects::nonNull).map(f -> mapFund(f)).collect(Collectors.toList());
		funds.setFundList(fundList);
		return fundList;
	}
	
	private List<Account> createAccountEntities(Accounts accounts) {
		if(accounts == null){
			return Collections.emptyList();
		}
		List<AccountPOJO> accountList = accounts.getAccountsList();
		List<Account> entities = accountList.stream().filter(Objects::nonNull).map(a -> createAccountEntity(a)).collect(Collectors.toList());
		return entities;
	}

	private Account createAccountEntity(AccountPOJO account) {
		Account accountEntity = new Account();
		accountEntity.setAccountid(account.getAccountid());
		accountEntity.setAccountName(account.getAccountName());
		accountEntity.setEffectiveDate(mappDateToEntity(account.getEffectiveDate()));
		List<Fund> fundEntities = createFundEntities(account.getFunds());
		for (Fund fund : fundEntities) {
			accountEntity.setFunds(fund);
		}
		return accountEntity;
	}
	
	private List<Fund> createFundEntities(Funds funds) {
		if (funds == null) {
			return Collections.emptyList();
		}
		List<FundPOJO> fundList = funds.getFundList();
		List<Fund> entities = fundList.stream().map(f -> createFundEntity(f)).collect(Collectors.toList());
		return entities;
	}
	

	private Fund createFundEntity(FundPOJO fund) {
		Fund fundEntity = new Fund();
		fundEntity.setFundId(fund.getFundId());
		fundEntity.setFundName(fund.getFundName());
		fundEntity.setSell(fund.getSell());
		fundEntity.setBuy(fund.getBuy());
		fundEntity.setYeild(fund.getYield());
		fundEntity.setEffectiveDate(mappDateToEntity(fund.getEffectiveDate()));
		return fundEntity;
	}

	private BankPOJO mapBank(Bank be) {
		BankPOJO bank = new BankPOJO();
		bank.setActive(be.getActive());
		bank.setBankCode(be.getBankCode());
		bank.setBankId(be.getBankId());
		bank.setBankName(be.getBankName());
		bank.setContactDetailsCode(be.getContactDetailsCode());
		bank.setEffectiveDate(mapToLocalDate(be.getEffectiveDate()));
		bank.setAccounts(mapAccounts(be.getAccounts()));
		return bank;
	}

	private AccountPOJO mapAccount(Account accountEntity) {
		AccountPOJO account = new AccountPOJO();
		account.setAccountid(accountEntity.getAccountid());
		account.setAccountName(accountEntity.getAccountName());
		account.setEffectiveDate(mapToLocalDate(accountEntity.getEffectiveDate()));
		account.setFunds(mapFunds(accountEntity.getFunds()));
		return account;
	}

	private FundPOJO mapFund(Fund fundEntity) {
		FundPOJO fund = new FundPOJO();
		fund.setFundId(fundEntity.getFundId());
		fund.setFundName(fundEntity.getFundName());
		fund.setSell(fundEntity.getSell());
		fund.setBuy(fundEntity.getBuy());
		fund.setYield(fundEntity.getYeild());
		fund.setEffectiveDate(mapToLocalDate(fundEntity.getEffectiveDate()));
		return fund;
	}

	Date mappDateToEntity(LocalDate date) {
		if (date != null) {
			return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	Timestamp mappDateTimeStampToEntity(LocalDateTime dateTime) {
		if (dateTime != null) {
			Timestamp timestamp = Timestamp.valueOf(dateTime);
			System.out.println(mapToLocalDateTime(timestamp));
			return Timestamp.valueOf(dateTime);
		}
		return null;
	}

	public LocalDate mapToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public LocalDateTime mapToLocalDateTime(Timestamp dateTime) {
		return dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}

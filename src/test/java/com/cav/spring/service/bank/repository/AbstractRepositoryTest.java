package com.cav.spring.service.bank.repository;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cav.spring.service.bank.AbstractTest;
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
import com.cav.spring.service.bank.repository.account.AccountRepository;
import com.cav.spring.service.bank.repository.account.AccountRepositoryImpl;
import com.cav.spring.service.bank.repository.bank.BankRepository;
import com.cav.spring.service.bank.repository.bank.BankRepositoryImpl;
import com.cav.spring.service.bank.repository.fund.FundRepository;
import com.cav.spring.service.bank.repository.fund.FundRepositoryImpl;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations="classpath:test.properties")
@Import({BankRepositoryImpl.class, AccountRepositoryImpl.class, FundRepositoryImpl.class})
public abstract class AbstractRepositoryTest extends AbstractTest {
	
	
	
	@Autowired
	protected BankRepository bankRepository;
	
	@Autowired
	protected AccountRepository accountRepository;
	
	@Autowired
	protected FundRepository fundRepository;
	
	public void setUp(){
		List<BankPOJO> bankList = createBankList();
		bankRepository.createBanks(bankList);
	}
	
	public void cleanUp(){
		bankRepository.removeBanks(createBankIds(DEFAULT_BANK_ID));
		Banks banks = bankRepository.listBanks(createBankIds(DEFAULT_BANK_ID));
		assertTrue(banks.getBankList().isEmpty());	
		
		List<AccountId> accountIds = createAccountsIds(DEFAULT_ACCOUNT_ID);
		accountRepository.removeAccounts(accountIds);
		Accounts accounts = accountRepository.listAccounts(accountIds);
		assertTrue(accounts.getAccountsList().isEmpty());
		
		accountIds = createAccountsIds(NEW_ACCOUNT_ID);
		accountRepository.removeAccounts(accountIds);
		accounts = accountRepository.listAccounts(accountIds);
		assertTrue(accounts.getAccountsList().isEmpty());
		
		List<FundId> fundIds = createFundsIds(NEW_FUND_ID);
		fundRepository.removeFunds(fundIds);
		List<FundPOJO> funds = fundRepository.listFunds(createIds(NEW_FUND_ID));
		assertTrue(funds.isEmpty());
		
		fundIds = createFundsIds(DEFAULT_FUND_ID);
		fundRepository.removeFunds(fundIds);
		funds = fundRepository.listFunds(createIds(DEFAULT_FUND_ID));
		assertTrue(funds.isEmpty());
	}
	
	

}

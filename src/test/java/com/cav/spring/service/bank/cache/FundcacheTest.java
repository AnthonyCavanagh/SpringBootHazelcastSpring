package com.cav.spring.service.bank.cache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cav.spring.service.bank.AbstractTest;
import com.cav.spring.service.bank.BankWebServiceApp;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.funds.FundPOJO;
import com.cav.spring.service.bank.model.funds.Funds;
import com.cav.spring.service.bank.repository.account.AccountRepository;
import com.cav.spring.service.bank.repository.bank.BankRepository;
import com.cav.spring.service.bank.repository.fund.FundRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BankWebServiceApp.class)
@TestPropertySource(locations="classpath:test.properties")
public class FundcacheTest extends AbstractTest {
	
	@InjectMocks
	private FundCache fundcache = new FundCacheImpl();
	
	@Autowired
	HazelcastInstance instance;
	
	@Mock
	private FundRepository fundRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	private BankRepository bankRepository;
	
	@Mock
	HazelcastInstance mockInstance;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listFundsGetsFromRepoistoryTest(){
		IMap<Object, Object> cache = createFundCache();
		
		when(mockInstance.getMap("FundCache")).thenReturn(cache);
		List <FundPOJO >funds = new ArrayList<FundPOJO>();
		funds.add(createFund(DEFAULT_FUND_ID));
		when(fundRepository.listFunds(createIds(DEFAULT_FUND_ID))).thenReturn(funds);
		fundcache.listFunds(createFundsIds(DEFAULT_FUND_ID));
		
		FundPOJO fund = (FundPOJO) cache.get(DEFAULT_FUND_ID);
		assertEquals(DEFAULT_FUND_ID,fund.getFundId());
		cache.evictAll();
	}
	
	@Test
	public void updateFundCacheTest(){
		IMap<Object, Object> cache = createFundCache();
		cache.put(DEFAULT_FUND_ID, createFund(DEFAULT_FUND_ID));
		
		FundPOJO fund = createFund(DEFAULT_FUND_ID);
		fund.setFundName("Updated Name");
		Funds funds = new Funds();
		funds.getFundList().add(fund);
		
		when(mockInstance.getMap("FundCache")).thenReturn(cache);
		fundcache.updateFunds(funds);
		fund = (FundPOJO) cache.get(DEFAULT_FUND_ID);
		assertEquals("Updated Name",fund.getFundName());
		cache.evictAll();
	}
	
	@Test
	public void updateFundsBanksTest(){
		IMap<Object, Object> cache = createFundCache();
		when(mockInstance.getMap("FundCache")).thenReturn(cache);
		
		List<BankPOJO> banks = createBankList();
		fundcache.updateFundsBanks(banks);
		FundPOJO fund = (FundPOJO) cache.get(DEFAULT_FUND_ID);
		assertEquals(DEFAULT_FUND_ID,fund.getFundId());
		cache.evictAll();
	}
	
	@Test
	public void updateFundsAccountsTest(){
		IMap<Object, Object> cache = createFundCache();
		when(mockInstance.getMap("FundCache")).thenReturn(cache);
		Accounts accounts = createAccountFund(DEFAULT_ACCOUNT_ID, DEFAULT_FUND_ID);
		fundcache.updateFundsAccount(accounts.getAccountsList());
		FundPOJO fund = (FundPOJO) cache.get(DEFAULT_FUND_ID);
		assertEquals(DEFAULT_FUND_ID,fund.getFundId());
		cache.evictAll();
	}
	

	private IMap<Object, Object> createFundCache() {
		IMap<Object, Object> fundCache = instance.getMap("FundCache");
		return fundCache;
	}

}

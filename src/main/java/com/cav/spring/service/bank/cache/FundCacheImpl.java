package com.cav.spring.service.bank.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cav.spring.service.bank.model.accounts.AccountPOJO;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.FundPOJO;
import com.cav.spring.service.bank.model.funds.Funds;
import com.cav.spring.service.bank.repository.account.AccountRepository;
import com.cav.spring.service.bank.repository.bank.BankRepository;
import com.cav.spring.service.bank.repository.fund.FundRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;


@Service
public class FundCacheImpl implements FundCache {
	
	
	@Autowired
	HazelcastInstance instance;
	
	@Autowired
	FundRepository fundRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	BankRepository bankRepository;

	@Override
	/**
	 * In this example using Write through, rather than just relying on the cache eviction
	 * policy. This is assuminng, that all updates from fund will come though this method
	 * only.
	 */
	public void updateFunds(Funds funds) {
		IMap<Long, FundPOJO> fundCache = instance.getMap("FundCache");
		cacheUpdateFunds(funds.getFundList(), fundCache);
		fundRepository.updateFunds(funds.getFundList());
	}

	@Override
	/**
	 * codes in the method, rather than annotation based, as I want to map each Id to each fund
	 * 
	 */
	public Funds listFunds(List<FundId> fundIds) {
		List<Long> ids = new ArrayList<Long>();
		List<FundPOJO> fundlist = new ArrayList<FundPOJO>();
		
		IMap<Long, FundPOJO> fundCache = instance.getMap("FundCache");
		for(FundId fundId : fundIds){
			FundPOJO fund = fundCache.get(fundId.getId());
			if(fund != null){
				fundlist.add(fund);
			} else {
				ids.add(fundId.getId());
			}
		}
		Funds funds = new Funds();
		funds.setFundList(fundlist);
		if(!ids.isEmpty()){
			List<FundPOJO> fundJPAlist = fundRepository.listFunds(ids);
			cacheFunds(fundJPAlist, fundCache);
			funds.getFundList().addAll(fundJPAlist);
		}
		return funds;
	}
	
	@Override
	/**
	 * Extracts new funds from accounts and adds to the funds cache
	 */
	public void updateFundsAccount(List<AccountPOJO> accounts) {
		IMap<Long, FundPOJO> fundCache = instance.getMap("FundCache");
		cacheFundsAccount(accounts, fundCache);
		accountRepository.updateAccounts(accounts);
	}

	
	@Override
	/**
	 * Extracts new funds from banks and adds to the funds cache
	 */
	public void updateFundsBanks(List<BankPOJO> banks) {
		IMap<Long, FundPOJO> fundCache = instance.getMap("FundCache");
		for(BankPOJO bank : banks){
			cacheFundsAccount(bank.getAccounts().getAccountsList(), fundCache);
		}
		bankRepository.createBanks(banks);
	}

	@Override
	/**
	 * Remove funds from the cache then the underlying DB
	 */
	public void removeFunds(List<FundId> fundIds) {
		IMap<Long, FundPOJO> fundCache = instance.getMap("FundCache");;
		for(FundId findId : fundIds){
			fundCache.evict(findId.getId());
		}
		fundRepository.removeFunds(fundIds);
	}
	
	private void cacheFundsAccount(List <AccountPOJO>accounts, IMap<Long, FundPOJO> fundCache){
		for(AccountPOJO account : accounts){
			cacheFunds(account.getFunds().getFundList(), fundCache);
		}
	}
	

	private void cacheFunds(List <FundPOJO>funds, IMap<Long, FundPOJO> fundCache){
		for(FundPOJO fund : funds){
			// Fund may already exist created as part of another account or bank create
			//Updates only by fund update.
			if(!fundCache.containsKey(fund.getFundId())){
				fundCache.put(fund.getFundId(),fund);
			}
		}
	}
	
	private void cacheUpdateFunds(List <FundPOJO>funds, IMap<Long, FundPOJO> fundCache){
		for(FundPOJO newFund : funds){
			FundPOJO oldFund = fundCache.get(newFund.getFundId());
			if(oldFund == null){
				//Log error fund has not been created.
			} else {
				fundCache.put(newFund.getFundId(), updateFund(oldFund, newFund));
			}
		}
	}
	
	private FundPOJO updateFund(FundPOJO fund, FundPOJO update) {
		if(fund.getFundName() != null){
			fund.setFundName(update.getFundName());
		}
		if(fund.getSell() != null){
			fund.setSell(update.getSell());
		}
		if(fund.getBuy() != null){
			fund.setBuy(update.getBuy());
		}
		if(fund.getYield() != null){
			fund.setYield(update.getYield());
		}
		return update;
	}

	

	

}

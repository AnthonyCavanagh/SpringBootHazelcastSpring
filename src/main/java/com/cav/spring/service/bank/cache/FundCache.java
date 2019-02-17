package com.cav.spring.service.bank.cache;

import java.util.List;

import com.cav.spring.service.bank.model.accounts.AccountPOJO;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.Funds;

public interface FundCache {
	public void updateFunds(Funds fund);
	public void updateFundsAccount(List <AccountPOJO> accounts);
	public void updateFundsBanks(List <BankPOJO> banks);
	public Funds listFunds(List <FundId> fundIds);
	public void removeFunds(List <FundId> fundIds);
}

package com.cav.spring.service.bank.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cav.spring.service.bank.cache.FundCache;
import com.cav.spring.service.bank.model.banks.BankId;
import com.cav.spring.service.bank.model.banks.Banks;
import com.cav.spring.service.bank.repository.bank.BankRepository;

@Service
public class BankServiceIpl implements BankService {
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	FundCache fundCache;
	
	
	@Override
	public void createBanks(Banks banks) {
		fundCache.updateFundsBanks(banks.getBankList());
	}

	@Override
	public void updateBanks(Banks banks) {
		fundCache.updateFundsBanks(banks.getBankList());
	}

	public Banks listBanks(List<BankId> bankIds) {
		return bankRepository.listBanks(bankIds);
	}
	
	@Override
	public void removeBanks(List<BankId> bankIds) {
		bankRepository.removeBanks(bankIds);
	}

	@Override
	public void removeAccountsBanks(List<BankId> bankIds) {
		bankRepository.removeAccounts(bankIds);
		
	}
	

}

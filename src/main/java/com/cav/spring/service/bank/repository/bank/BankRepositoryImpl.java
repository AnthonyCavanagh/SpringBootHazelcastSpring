package com.cav.spring.service.bank.repository.bank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cav.spring.service.bank.entity.Account;
import com.cav.spring.service.bank.entity.Bank;
import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.banks.BankId;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.banks.Banks;
import com.cav.spring.service.bank.repository.account.AccountJPARepository;
import com.cav.spring.service.bank.repository.fund.FundJpaRepository;
import com.cav.spring.service.bank.service.MappEntitiesAbstract;

@Service
public class BankRepositoryImpl extends MappEntitiesAbstract implements BankRepository {

	@Autowired
	private BankJPARepository bankRepository;

	@Autowired
	private AccountJPARepository accountRepository;
	
	@Autowired
	FundJpaRepository fundRepository;

	@Override
	public void createBanks(List<BankPOJO> bankList) {
		List<Bank> entities = new ArrayList<Bank>();
		for (BankPOJO bank : bankList) {
			Bank bankEntity = bankRepository.findOne(bank.getBankId());
			if (bankEntity == null) {
				entities.add(createBankEntity(bank));
			} else {
				// Log error bank exists.
				entities.add(updateBankEntity(bank, bankEntity));
			}
		}
		bankRepository.save(entities);
	}

	@Override
	public void updateBanks(List<BankPOJO> bankList) {
		List<Bank> entities = new ArrayList<Bank>();
		for (BankPOJO bank : bankList) {
			Bank bankEntity = bankRepository.findOne(bank.getBankId());
			if (bankEntity != null) {
				entities.add(updateBankEntity(bank, bankEntity));
			} else {
				// Log error doesnt exists.
			}
		}
		bankRepository.save(entities);
	}

	@Override
	public Banks listBanks(List<BankId> bankIds) {
		List<Bank> bankList = new ArrayList<Bank>();
		for (BankId bankId : bankIds) {
			Bank bankEntity = bankRepository.findOne(bankId.getId());
			if (bankEntity != null) {
				bankList.add(bankEntity);
			}
		}
		return mapdBanks(bankList);
	}

	@Override
	public void removeBanks(List<BankId> bankIds) {
		for (BankId bankId : bankIds) {
			try {
				bankRepository.delete(bankId.getId());
			} catch (EmptyResultDataAccessException e) {
				//Do nothing
			}
		}
	}

	@Override
	public void removeAccounts(List<BankId> bankIds) {
		for (BankId bankId : bankIds) {
			Bank bank = bankRepository.findOne(bankId.getId());
			if (bank != null) {
				for (AccountId accountId : bankId.getAccountIds()) {
					Account account = accountRepository.findOne(accountId.getId());
					account.removeAllFunds();
					bank.removeAccounts(account);
				}
			}
			bankRepository.save(bank);
		}
	}
}

package com.cav.spring.service.bank.repository.bank;

import org.springframework.stereotype.Repository;
import com.cav.spring.service.bank.entity.Bank;
import com.cav.spring.service.bank.model.accounts.AccountId;
import com.cav.spring.service.bank.model.banks.BankId;
import com.cav.spring.service.bank.model.banks.BankPOJO;
import com.cav.spring.service.bank.model.banks.Banks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BankRepository {

	public void createBanks(List <BankPOJO> banks);
	public void updateBanks(List <BankPOJO> banks);
	public Banks listBanks(List <BankId> bankIds);
	public void removeBanks(List <BankId> bankIds);
	public void removeAccounts(List <BankId> bankIds);
}

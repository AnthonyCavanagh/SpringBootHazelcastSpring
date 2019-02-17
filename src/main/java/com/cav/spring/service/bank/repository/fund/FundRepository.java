package com.cav.spring.service.bank.repository.fund;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cav.spring.service.bank.entity.Fund;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.FundPOJO;
import com.cav.spring.service.bank.model.funds.Funds;

public interface FundRepository {

	public void updateFunds(List <FundPOJO> funds);
	public List<FundPOJO> listFunds(List <Long> fundIds);
	public void removeFunds(List <FundId> fundIds);
}

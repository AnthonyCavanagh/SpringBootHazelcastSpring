package com.cav.spring.service.bank.repository.fund;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cav.spring.service.bank.controller.BankServiceController;
import com.cav.spring.service.bank.entity.Fund;
import com.cav.spring.service.bank.model.funds.FundId;
import com.cav.spring.service.bank.model.funds.FundPOJO;
import com.cav.spring.service.bank.service.MappEntitiesAbstract;

@Service
public class FundRepositoryImpl extends MappEntitiesAbstract  implements FundRepository {
	
	@Autowired
	FundJpaRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(FundRepositoryImpl.class);	

	@Override
	public void updateFunds(List<FundPOJO> fundList) {
		List<Fund> entities = new ArrayList<Fund>();;
		for(FundPOJO fund : fundList){
			Fund fundEntity = repository.findOne(fund.getFundId());
			if(fundEntity != null){
				entities.add(updateFundEntity(fund, fundEntity));
			} else {
				//error
			}
		}
		repository.save(entities);
	}

	@Override
	public List<FundPOJO> listFunds(List<Long> fundIds) {
		List<Fund> flist = repository.findAll(fundIds);
		List<FundPOJO> fundJPAlist = mapFundList(flist);
		return fundJPAlist;
	}

	@Override
	public void removeFunds(List<FundId> fundIds) {
		for (FundId fundId : fundIds) {
			try {
				repository.delete(fundId.getId());
			} catch (EmptyResultDataAccessException e) {
				// Do nothing
			}
		}
	}

}

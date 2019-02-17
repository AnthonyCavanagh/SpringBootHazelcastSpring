package com.cav.spring.service.bank.model.banks;

import java.util.ArrayList;
import java.util.List;

public class Banks {

	private List <BankPOJO> bankList = new ArrayList<BankPOJO>();

	public List<BankPOJO> getBankList() {
		return bankList;
	}

	public void setBankList(List<BankPOJO> bankList) {
		this.bankList = bankList;
	}
}

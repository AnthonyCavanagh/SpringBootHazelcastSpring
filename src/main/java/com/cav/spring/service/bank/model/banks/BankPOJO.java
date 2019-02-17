package com.cav.spring.service.bank.model.banks;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.cav.spring.service.bank.model.accounts.Accounts;

public class BankPOJO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long bankId;
	private String bankCode;
	private String bankName;
	private Integer contactDetailsCode;
	private LocalDate effectiveDate;
	private String active;
	private Accounts accounts;
	
	public long getBankId() {
		return bankId;
	}
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setContactDetailsCode(Integer contactDetailsCode) {
		this.contactDetailsCode = contactDetailsCode;
	}

	public String getBankCode() {
		return bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public Integer getContactDetailsCode() {
		return contactDetailsCode;
	}
	
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	public Accounts getAccounts() {
		return accounts;
	}
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", bankCode=" + bankCode + ", bankName=" + bankName + ", contactDetailsCode="
				+ contactDetailsCode + ", effectiveDate=" + effectiveDate + ", active=" + active + ", accounts="
				+ accounts + "]";
	}

}

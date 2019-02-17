package com.cav.spring.service.bank.model.accounts;

import java.io.Serializable;
import java.time.LocalDate;

import com.cav.spring.service.bank.model.funds.Funds;

public class AccountPOJO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long accountid;
    protected String accountName;
    protected LocalDate effectiveDate;
    protected String active;
    protected Funds funds;
	
	public long getAccountid() {
		return accountid;
	}
	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	public Funds getFunds() {
		return funds;
	}
	public void setFunds(Funds funds) {
		this.funds = funds;
	}
	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", accountName=" + accountName + ", effectiveDate=" + effectiveDate
				+ ", active=" + active + ", funds=" + funds + "]";
	}
}

package com.cav.spring.service.bank.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.JoinColumn;


@Entity(name = "Account")
@Table(name = "Account")
public class Account {
	
	@Id
	@Column(name="ACCOUNT_ID")
	private long accountid;
	
	@Column(name="ACCOUNT_NAME")
	private String accountName;
	
	@Column(name="EFFECTIVE_DATE")
	private Date effectiveDate;
	
	@Column(name="ACCOUNT_ACTIVE")
	private String accountActive;
	
	@ManyToMany(mappedBy = "accounts")
    private Set<Bank> banks = new HashSet<Bank>();
	
	@ManyToMany(cascade = { 
			CascadeType.PERSIST, CascadeType.MERGE
	    })
	    @JoinTable(name = "ACCOUNT_FUND",
	        joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
	        inverseJoinColumns = @JoinColumn(name = "FUND_ID")
	    )
	private Set<Fund> funds = new HashSet<Fund>();

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

	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getAccountActive() {
		return accountActive;
	}
	public void setAccountActive(String accountActive) {
		this.accountActive = accountActive;
	}
	public Set<Bank> getBanks() {
		return banks;
	}
	public void setBanks(Set<Bank> banks) {
		this.banks = banks;
	}
	
	public Set<Fund> getFunds() {
		return funds;
	}
	
	public void setFunds(Fund fund) {
		funds.add(fund);
		fund.getAccounts().add(this);
	}
	
	public void removeFunds(Fund fund) {
		funds.remove(fund);
		fund.getAccounts().remove(this);
	}
	
	public void removeAllFunds() {
		for(Fund fund : getFunds()){
			funds.remove(fund);
			fund.getAccounts().remove(this);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountActive == null) ? 0 : accountActive.hashCode());
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + (int) (accountid ^ (accountid >>> 32));
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountid != other.accountid)
			return false;
		if (accountActive == null) {
			if (other.accountActive != null)
				return false;
		} else if (!accountActive.equals(other.accountActive))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		return true;
	}
}

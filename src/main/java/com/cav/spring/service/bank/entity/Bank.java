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

@Entity
@Table(name = "Bank")
public class Bank {

	@Id
	@Column(name = "BANK_ID")
	private long bankId;

	@Column(name = "BANK_CODE")
	private String bankCode;

	@Column(name = "BANK_NAME")
	private String bankName;

	@Column(name = "CONTACT_DETAILS_CODE")
	private int contactDetailsCode;

	@Column(name = "EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name = "BANK_ACTIVE")
	private String active;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, })
	@JoinTable(name = "BANK_ACCOUNT", joinColumns = @JoinColumn(name = "BANK_ID"), inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
	private Set<Account> accounts = new HashSet<Account>();

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getContactDetailsCode() {
		return contactDetailsCode;
	}

	public void setContactDetailsCode(int contactDetailsCode) {
		this.contactDetailsCode = contactDetailsCode;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Account account) {
		accounts.add(account);
		account.getBanks().add(this);
	}

	public void removeAccounts(Account account) {
		accounts.remove(account);
		account.getBanks().remove(this);
	}

	public void removeAllAccounts() {
		Set<Account> accountSet = getAccounts();
		for (Account account : accountSet ) {
			account.removeAllFunds();
			accounts.remove(account);
			account.getBanks().remove(this);
			if(accountSet == null || accountSet.isEmpty()){
				break;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
		result = prime * result + (int) (bankId ^ (bankId >>> 32));
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + contactDetailsCode;
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
		Bank other = (Bank) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (bankCode == null) {
			if (other.bankCode != null)
				return false;
		} else if (!bankCode.equals(other.bankCode))
			return false;
		if (bankId != other.bankId)
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (contactDetailsCode != other.contactDetailsCode)
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankEntity [bankId=" + bankId + ", bankCode=" + bankCode + ", bankName=" + bankName
				+ ", contactDetailsCode=" + contactDetailsCode + ", effectiveDate=" + effectiveDate + ", active="
				+ active + "]";
	}
}

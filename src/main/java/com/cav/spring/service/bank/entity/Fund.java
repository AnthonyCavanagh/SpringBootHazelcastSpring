package com.cav.spring.service.bank.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

@Entity(name = "Fund")
@Table(name = "Fund")
public class Fund {
	
	@Id
	@Audited
	@Column(name="FUND_ID")
	private long fundId;
	
	@Column(name="FUND_NAME")
	@Audited
	private String fundName;
	
	@Audited
	@Column(name="BUY")
	private BigDecimal buy;
	
	@Audited
	@Column(name="SELL")
	private BigDecimal sell;
	
	@Audited
	@Column(name="YEILD")
	private Float yeild;
	
	@Column(name="EFFECTIVE_DATE")
	private Date effectiveDate;
	
	@Audited
	@UpdateTimestamp
	private Timestamp fundTimeStamp;
	
	@ManyToMany(mappedBy = "funds")
    private Set<Account> accounts = new HashSet<Account>();
	
	public long getFundId() {
		return fundId;
	}
	public void setFundId(long fundId) {
		this.fundId = fundId;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public BigDecimal getBuy() {
		return buy;
	}
	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}
	public BigDecimal getSell() {
		return sell;
	}
	public void setSell(BigDecimal sell) {
		this.sell = sell;
	}
	
	public Float getYeild() {
		return yeild;
	}
	public void setYeild(Float yeild) {
		this.yeild = yeild;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	public Set<Account> getAccounts() {
		return accounts;
	}
	
	public Timestamp getFundTimeStamp() {
		return fundTimeStamp;
	}
	public void setFundTimeStamp(Timestamp fundTimeStamp) {
		this.fundTimeStamp = fundTimeStamp;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buy == null) ? 0 : buy.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + (int) (fundId ^ (fundId >>> 32));
		result = prime * result + ((fundName == null) ? 0 : fundName.hashCode());
		result = prime * result + ((fundTimeStamp == null) ? 0 : fundTimeStamp.hashCode());
		result = prime * result + ((sell == null) ? 0 : sell.hashCode());
		result = prime * result + ((yeild == null) ? 0 : yeild.hashCode());
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
		Fund other = (Fund) obj;
		if (buy == null) {
			if (other.buy != null)
				return false;
		} else if (!buy.equals(other.buy))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (fundId != other.fundId)
			return false;
		if (fundName == null) {
			if (other.fundName != null)
				return false;
		} else if (!fundName.equals(other.fundName))
			return false;
		if (fundTimeStamp == null) {
			if (other.fundTimeStamp != null)
				return false;
		} else if (!fundTimeStamp.equals(other.fundTimeStamp))
			return false;
		if (sell == null) {
			if (other.sell != null)
				return false;
		} else if (!sell.equals(other.sell))
			return false;
		if (yeild == null) {
			if (other.yeild != null)
				return false;
		} else if (!yeild.equals(other.yeild))
			return false;
		return true;
	}
}

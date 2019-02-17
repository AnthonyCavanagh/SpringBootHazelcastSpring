package com.cav.spring.service.bank.model.funds;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FundPOJO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long fundId;
    private String fundName;
    private BigDecimal buy;
    private BigDecimal  sell;
    private Float yield;
    private LocalDate effectiveDate;
   
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
	public Float getYield() {
		return yield;
	}
	public void setYield(Float yield) {
		this.yield = yield;
	}
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
	public String toString() {
		return "Fund [fundId=" + fundId + ", fundName=" + fundName + ", buy=" + buy + ", sell=" + sell + ", yield="
				+ yield + ", effectiveDate=" + effectiveDate + "]";
	}
}

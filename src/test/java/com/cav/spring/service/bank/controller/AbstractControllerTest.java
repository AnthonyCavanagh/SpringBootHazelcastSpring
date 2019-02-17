package com.cav.spring.service.bank.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cav.spring.service.bank.AbstractTest;
import com.cav.spring.service.bank.model.accounts.AccountRequest;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.model.banks.BankRequest;
import com.cav.spring.service.bank.model.banks.Banks;
import com.cav.spring.service.bank.model.funds.FundRequest;
import com.cav.spring.service.bank.model.funds.Funds;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@RunWith(SpringRunner.class)
@WebMvcTest(BankServiceController.class)
public abstract class AbstractControllerTest extends AbstractTest  {
	
	protected MockMvc mvc;
	   @Autowired
	   WebApplicationContext webApplicationContext;

	   protected void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	   protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper mapper = new ObjectMapper();
	      mapper.registerModule(new JavaTimeModule());
	      mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	      mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	      ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	      return ow.writeValueAsString(obj);
	   }
	   protected <T> T mapFromXML(String json, Class<T> clazz)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.readValue(json, clazz);
	   }
	   
	   protected Banks createBanks(){
			Banks banks = new Banks();
			banks.setBankList(createBankList());
			return banks;
		}
		
		protected Accounts updateAccounts() {
			return createAccountFund(DEFAULT_ACCOUNT_ID,DEFAULT_FUND_ID);
		}
		
		protected Funds updateFunds(){
			return updateFund(DEFAULT_FUND_ID, "USA ESG Select Fund");
		}
		
		protected BankRequest createBankRequest(){
			BankRequest request = new BankRequest();
			request.setBankIds(createBankIds(DEFAULT_BANK_ID));
			return request;
		}
		
		protected AccountRequest createAccountRequest(){
			AccountRequest request = new AccountRequest();
			request.setAccountIds(createAccountsIds(DEFAULT_ACCOUNT_ID));
			return request ;
		}
		
		protected FundRequest createFundRequest(){
			FundRequest request = new FundRequest();
			request.setFundIds(createFundsIds(DEFAULT_FUND_ID));
			return request ;
		}
}

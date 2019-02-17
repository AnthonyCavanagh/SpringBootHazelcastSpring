package com.cav.spring.service.bank.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cav.spring.service.bank.model.accounts.AccountRequest;
import com.cav.spring.service.bank.model.accounts.Accounts;
import com.cav.spring.service.bank.model.banks.BankRequest;
import com.cav.spring.service.bank.model.banks.Banks;
import com.cav.spring.service.bank.model.funds.FundRequest;
import com.cav.spring.service.bank.model.funds.Funds;
import com.cav.spring.service.bank.service.AccountService;
import com.cav.spring.service.bank.service.BankService;
import com.cav.spring.service.bank.service.FundService;

@RestController
@RequestMapping("/bankservice")
public class BankServiceController {

	@Autowired
	BankService bankService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	FundService fundService;
	
	private static final Logger logger = LoggerFactory.getLogger(BankServiceController.class);	


	@RequestMapping(value = "/createbanks/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBanks(@RequestBody Banks banks, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Modify Banks");
		bankService.createBanks(banks);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updatebanks/", method = RequestMethod.POST)
	public ResponseEntity<Void> updateBanks(@RequestBody Banks banks, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Modify Banks");
		bankService.updateBanks(banks);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/findbanks/", method = RequestMethod.POST)
	public ResponseEntity<Banks> findBanks(@RequestBody BankRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Find Banks");
		Banks banks = bankService.listBanks(request.getBankIds());
		return new ResponseEntity<Banks>(banks, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/removebanks/", method = RequestMethod.POST)
	public ResponseEntity<Void> removeBanks(@RequestBody BankRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Remove Banks");
		bankService.removeBanks(request.getBankIds());
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/removeaccountsbanks/", method = RequestMethod.POST)
	public ResponseEntity<Void> removeAccountsBanks(@RequestBody BankRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Remove Accounts from Banks");
		bankService.removeAccountsBanks(request.getBankIds());
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findaccounts/", method = RequestMethod.POST)
	public ResponseEntity<Accounts> findAccounts(@RequestBody AccountRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Find Accounts");
		Accounts accounts = accountService.listAccounts(request.getAccountIds());
		return new ResponseEntity<Accounts>(accounts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateaccounts/", method = RequestMethod.POST)
	public ResponseEntity<Void> modifyAccounts(@RequestBody Accounts accounts, UriComponentsBuilder ucBuilder)
			throws ParseException {
		System.out.println("Modify Account");
		accountService.updateAccounts(accounts);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/removeaccounts/", method = RequestMethod.POST)
	public ResponseEntity<Void> removeAccounts(@RequestBody AccountRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		System.out.println("Remove Account");
		accountService.removeAccounts(request.getAccountIds());
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/removefundsaccounts/", method = RequestMethod.POST)
	public ResponseEntity<Void> removeFundsAccounts(@RequestBody AccountRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		System.out.println("Remove Funds from Account");
		accountService.removeFunds(request.getAccountIds());
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatefunds/", method = RequestMethod.POST)
	public ResponseEntity<Void> modifyFunds(@RequestBody Funds funds, UriComponentsBuilder ucBuilder)
			throws ParseException {
		System.out.println("Update Funds");
		fundService.updateFunds(funds);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findfunds/", method = RequestMethod.POST)
	public ResponseEntity<Funds> findFunds(@RequestBody FundRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Find Funds");
		Funds funds = fundService.listFunds(request.getFundIds());
		return new ResponseEntity<Funds>(funds, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/removefunds/", method = RequestMethod.POST)
	public ResponseEntity<Void> removeFunds(@RequestBody FundRequest request, UriComponentsBuilder ucBuilder)
			throws ParseException {
		logger.info("Remove Funds");
		fundService.removeFunds(request.getFundIds());
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
}

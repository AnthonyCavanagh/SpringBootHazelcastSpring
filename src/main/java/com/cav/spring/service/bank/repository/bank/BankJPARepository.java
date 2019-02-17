package com.cav.spring.service.bank.repository.bank;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cav.spring.service.bank.entity.Bank;

public interface BankJPARepository extends JpaRepository<Bank, Long> {

}

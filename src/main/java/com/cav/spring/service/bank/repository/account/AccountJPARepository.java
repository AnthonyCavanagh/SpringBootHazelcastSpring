package com.cav.spring.service.bank.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cav.spring.service.bank.entity.Account;


public interface AccountJPARepository extends JpaRepository<Account, Long>{

}

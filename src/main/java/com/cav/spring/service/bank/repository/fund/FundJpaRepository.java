package com.cav.spring.service.bank.repository.fund;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cav.spring.service.bank.entity.Fund;

public interface FundJpaRepository extends JpaRepository<Fund, Long>  {

}

package com.cav.spring.service.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(scanBasePackages={"com.cav.spring.service.bank"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableCaching
public class BankWebServiceApp {
	
	   public static void main(String[] args) {
	        SpringApplication.run(BankWebServiceApp.class, args);
	    }

}

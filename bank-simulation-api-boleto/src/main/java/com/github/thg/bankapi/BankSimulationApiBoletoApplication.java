package com.github.thg.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BankSimulationApiBoletoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSimulationApiBoletoApplication.class, args);
	}

}

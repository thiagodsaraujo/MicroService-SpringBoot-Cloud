package com.github.thg.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BankSimulationApiBoleto1Application {

	public static void main(String[] args) {
		SpringApplication.run(BankSimulationApiBoleto1Application.class, args);
	}

}

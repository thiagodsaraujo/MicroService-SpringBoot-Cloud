package com.github.thg.bankapi.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.github.thg.bankapi.dto.ContaDto;
import com.github.thg.bankapi.entities.Conta;

//@FeignClient(name = "bank-simulation-api", path = "/conta")
@FeignClient(value="conta", url = "http://localhost:8080/conta")
public interface ContaRequest {
	
	
	@GetMapping("/{id}")
	ContaDto findById(@PathVariable Long id);
	
	@GetMapping("/test")
	String getTest();
	
	@GetMapping("/listContas")
	ResponseEntity<List<Conta>> listContas();
	
	@PutMapping("/attSaldo/{id}/{valor}")
	ResponseEntity<Conta> generatePayment(@PathVariable Long id,
			@PathVariable Double valor);
	
	@PutMapping("/attslip/{id}/{valor}")
	ResponseEntity<Conta> generatePaymentSlip(@PathVariable Long id,
			@PathVariable Double valor);
	
} 

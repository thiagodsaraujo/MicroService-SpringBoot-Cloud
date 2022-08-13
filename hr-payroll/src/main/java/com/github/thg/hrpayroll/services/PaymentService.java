package com.github.thg.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.github.thg.hrpayroll.entities.Payment;

@Service
public class PaymentService {
	// Criamos um service ao inves de um repositorio, por que esse projeto especifico diferente do worker
	// não utiliza banco de dados, vai estanciar os pagamentos com a regra de negócio do curso

	public Payment getPayment(long workerId, int days) {
		// instanciar um pagamento mockado(hard code) para testar
		return new Payment("Bob", 200.0, days);
	}
	
	// para testar vamos criar um resource que cria o endpoint
}

package com.github.thg.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.thg.hrpayroll.entities.Payment;
import com.github.thg.hrpayroll.entities.Worker;

@Service
public class PaymentService {
	// Criamos um service ao inves de um repositorio, por que esse projeto especifico diferente do worker
	// não utiliza banco de dados, vai estanciar os pagamentos com a regra de negócio do curso

	// vai colocar a propriedade que criou no app.propeties
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;
	// Vamos usar para fazer uma chamada no meu webservice la do projeto Workers, igual faz a chamada no postman
	
	
	
	public Payment getPayment(long workerId, int days) {
		// Agora vamos fazer uma requisição de uma API externa usando rest template
		// Como não tinhamos a classe Worker dentro desse projeto, colamos aqui e deletamos as JPAS
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", ""+workerId);
		Worker worker = restTemplate.getForObject(workerHost +"/workers/{id}", Worker.class,uriVariables);
		return new Payment(worker.getName(),worker.getDailyIncome(), days);
	}
	
	// para testar vamos criar um resource que cria o endpoint
}

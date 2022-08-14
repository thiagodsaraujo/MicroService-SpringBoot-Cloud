package com.github.thg.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.thg.hrpayroll.entities.Payment;
import com.github.thg.hrpayroll.entities.Worker;
import com.github.thg.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	// Criamos um service ao inves de um repositorio, por que esse projeto especifico diferente do worker
	// não utiliza banco de dados, vai estanciar os pagamentos com a regra de negócio do curso

	// vai colocar a propriedade que criou no app.propeties
	// A comunicação entre dois projetos diferentes, o host de forma estatica(hard code)
	// microserviço não é dessa forma, voce chama pelo nome e a infraestrutura vai resolver e devolver o dado
//	@Value("${hr-worker.host}") HARCODE
//	private String workerHost;	HARDCODE
	
//	@Autowired
//	private RestTemplate restTemplate;
//	// Vamos usar para fazer uma chamada no meu webservice la do projeto Workers, igual faz a chamada no postman
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	
	public Payment getPayment(long workerId, int days) {
		// Agora vamos fazer uma requisição de uma API externa usando rest template
		// Como não tinhamos a classe Worker dentro desse projeto, colamos aqui e deletamos as JPAS
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", ""+workerId);
//		Worker worker = restTemplate.getForObject(workerHost +"/workers/{id}", Worker.class,uriVariables);
		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(),worker.getDailyIncome(), days);
	}
	
	// para testar vamos criar um resource que cria o endpoint
}

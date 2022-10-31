package com.github.thg.bankapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.thg.bankapi.entities.Conta;
import com.github.thg.bankapi.entities.Extract;
import com.github.thg.bankapi.entities.enums.TipoOperacao;
import com.github.thg.bankapi.repositories.ContaRepository;
import com.github.thg.bankapi.services.ContaService;
import com.github.thg.bankapi.services.ExtractService;
import com.github.thg.bankapi.services.exceptions.SaldoInsuficienteException;
import com.github.thg.bankapi.services.exceptions.ValorInvalidoException;
import com.github.thg.bankapi.util.model.DataUtil;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	DataUtil dataUtil;
	
	@Autowired
	ContaRepository contaRepository;
	
	
	@Autowired
	ExtractService extractService;
	
	
	@GetMapping("/test")
	public String getTest() {
		return "Isso é um teste Feign2";
	}
	
	@GetMapping("/listContas")
	public ResponseEntity<List<Conta>> listAll(){
		List<Conta> contas = contaService.listAll();
		return ResponseEntity.ok(contas);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Conta> findById(@PathVariable Long id) {
		Conta contaSearched = contaService.search(id);
		if(contaSearched == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contaSearched);
	}
	
	@PutMapping(value = "/attSaldo/{id}/{valor}")
	public ResponseEntity<Conta> getPayment(@PathVariable Long id, 
			@PathVariable Double valor) throws SaldoInsuficienteException{
		
		return ResponseEntity.ok(contaService.getPayment(id, valor));
	}
	
	@PutMapping(value = "/attslip/{id}/{valor}")
	public ResponseEntity<Extract> getPaymentSlip(@PathVariable Long id, 
			@PathVariable Double valor) throws Exception, SaldoInsuficienteException{
		
		Conta contaDeposito = contaRepository.findById(id)
				.orElseThrow(()-> new Exception("Conta not found for this id :" + id));
		
		// Devia atualizar o saldo por um método do service, ta errado
		Double saldoAnterior = contaDeposito.getSaldo();
		
		if (contaDeposito.getSaldo() >= valor) {
			contaDeposito.setSaldo(contaDeposito.getSaldo() - valor);
		} else {
			throw new SaldoInsuficienteException("Saldo Insuficiente!!");
		}
		Extract extractSlip = extractService.generateSlip(contaDeposito, TipoOperacao.PAGAMENTO, saldoAnterior,
				contaDeposito.getSaldo(), valor);
		
		ResponseEntity.ok(contaService.getPayment(id, valor));
		return ResponseEntity.ok(extractSlip);
	}
	
	
	
	@PostMapping(value = "/createConta")
	public ResponseEntity<Conta> createConta( 
			@RequestBody Conta conta){
		
		final Conta updateConta = contaRepository.save(conta);
		
		return ResponseEntity.ok(updateConta);
		
	}
	
	@PostMapping(value = "/addConta")
	public ResponseEntity<Void> addBank(@Validated @RequestBody Conta conta){
		Conta registredConta = contaService.save(conta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registredConta.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateConta(@PathVariable Long id, @Validated @RequestBody Conta newConta){
		Conta contaSearched = contaService.update(newConta);
		contaSearched.setId(id);
		contaSearched = contaService.update(newConta);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/extracts/{id}")
	public ResponseEntity<List<Extract>> listExtracts(@PathVariable Long id){
		List<Extract> allByConta = extractService.listAllByConta(id);
		return ResponseEntity.ok(allByConta);
	}

	
	@PutMapping(value = "/updateConta/{id}")
	public ResponseEntity<Conta> updateBankById(@PathVariable Long id, 
			@RequestBody Conta contaDetails)
	throws Exception {
		Conta conta = contaRepository.findById(id)
				.orElseThrow(() -> new Exception("Conta not found for this id :" + id));
		
//		agency.setId(agencyDetails.getId());
		conta.setClient(contaDetails.getClient());
		conta.setAgency(contaDetails.getAgency());
		conta.setNumber(contaDetails.getNumber());
		conta.setSaldo(contaDetails.getSaldo());
		
		Conta updateConta = contaRepository.save(conta);
		
		return ResponseEntity.ok(updateConta);

	}
	
	
	@PostMapping("/{id}/depositar/{valor}")
	public ResponseEntity<Extract> depositarExtrato(@PathVariable Long id, @PathVariable Double valor) throws Exception{
		
		Conta contaDeposito = contaRepository.findById(id)
				.orElseThrow(()-> new Exception("Conta not found for this id :" + id));
		
		// Devia atualizar o saldo por um método do service, ta errado
		Double saldoAnterior = contaDeposito.getSaldo();
		contaDeposito.setSaldo(contaDeposito.getSaldo() + valor);
		
		// Devia atualizar o saldo por um método do service, ta errado
		final Conta updateConta = contaRepository.save(contaDeposito);
		
		Double saldoAtual = updateConta.getSaldo();
		
		Extract extractDebit = extractService.generate(false, updateConta, TipoOperacao.DEPOSITO, saldoAnterior, saldoAtual, valor);
		return ResponseEntity.ok(extractDebit);
	}
	
	@PostMapping("/{id}/sacar/{valor}")
	public ResponseEntity<Extract> sacarExtrato(@PathVariable Long id, @PathVariable Double valor) throws Exception{
		Conta contaDeposito = contaRepository.findById(id)
				.orElseThrow(()-> new Exception("Account not found for this id :" + id));
		
		Double saldoAnterior = contaDeposito.getSaldo();
		
		if (valor == 0 || valor == null) {
			throw new ValorInvalidoException("Valor inválido! : "
					+ valor
					+ " Valor deve ser diferente de vazio "
					+ "e não pode ser 0");
		}
		
		if (contaDeposito.getSaldo() < valor) {
			throw new SaldoInsuficienteException("Saldo Insuficiente! Saldo Atual: R$"
					+ contaDeposito.getSaldo() + ", Valor da Operação: R$ " + valor);
		}
		
		contaDeposito.setSaldo(contaDeposito.getSaldo() - valor);
		
		final Conta updateConta = contaRepository.save(contaDeposito);
		
		Double saldoAtual = updateConta.getSaldo();
		
		// gera o extrato
		Extract extractDebit = extractService.generate(true, updateConta, TipoOperacao.SAQUE,saldoAnterior,saldoAtual, valor);
		
		return ResponseEntity.ok(extractDebit);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		contaService.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id){
		contaService.remove(id);
		return ResponseEntity.noContent().build();
	}

}

package com.github.thg.bankapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thg.bankapi.dto.ContaDto;
import com.github.thg.bankapi.dto.PaymentSlipDto;
import com.github.thg.bankapi.entities.Conta;
import com.github.thg.bankapi.entities.PaymentSlip;
import com.github.thg.bankapi.repositories.PaymentSlipRepository;
import com.github.thg.bankapi.request.ContaRequest;
import com.github.thg.bankapi.service.PaymentSlipService;


@RestController
@RequestMapping("/payments")
public class PaymentSlipController {
	

	@Autowired
	PaymentSlipRepository repository;
	
//	@Autowired
//	private PaymentSlipService service;
	
	@Autowired
	ContaRequest contaRequest;
	
	@Autowired
	PaymentSlipService paymentSlipService;
	
	
	
//	@PostMapping(value = "/{contaId}/{valueSlip}")
//	public ResponseEntity<PaymentSlip> getPaymentSlip(@PathVariable Long contaId, @PathVariable Double valueSlip){
//		PaymentSlip registerPayment = new PaymentSlip(true, valueSlip, contaId);
//		PaymentSlip paymentSlip = service.getPayment(contaId, valueSlip, "123");
//		System.out.println("Pagemtno realizado!");
//		return new ResponseEntity(repository.save(paymentSlip),HttpStatus.CREATED);
//	}
	
	@GetMapping("slip/{id}")
	public final PaymentSlip findById(@PathVariable Long id){
		return paymentSlipService.findById(id);
	}
	
	
	@GetMapping("/test")
	public String getTest() {
		return contaRequest.getTest();
	}
	
	@GetMapping("/listSlips")
	public ResponseEntity<List<PaymentSlip>> listAllSlips(){
		List<PaymentSlip> boletos = paymentSlipService.findAll();
		return ResponseEntity.ok(boletos);
	}
	
	@PostMapping("boletoTeste/{id}/{valor}")
	public boolean generatePaymentSlip(@PathVariable Long id,
			@PathVariable Double valor) {
		if (contaRequest.generatePayment(id, valor) != null) {
			return true;
		}
		return false;
	}
	
	@PostMapping("conta/{contaId}/boleto/{slipId}")
	public String generatePaymentSlip2(
			@PathVariable Long contaId,
			@PathVariable Long slipId) {
		PaymentSlip boleto = paymentSlipService.findById(slipId);
		if (boleto != null) {
			System.out.println(boleto.getValueSlip());
			System.out.println(boleto.toString());
		}		
		if (contaRequest.generatePayment(contaId, boleto.getValueSlip()) != null) {
			HttpStatus ok = HttpStatus.OK;
			return "Código do Boleto " + boleto.getId() + boleto.getCodeSlip()
			+ " / Valor: R$ " + boleto.getValueSlip() +
			" / Pago com Sucesso! " + ok;
		}
		return "Erro ao pagar o boleto";
	}
	
	@PostMapping("pgto/conta/{contaId}/boleto/{slipId}")
	public String generatePaymentSlip3(
			@PathVariable Long contaId,
			@PathVariable Long slipId) {
		PaymentSlip boleto = paymentSlipService.findById(slipId);	
		if (contaRequest.generatePaymentSlip(contaId, boleto.getValueSlip()) != null) {
			return "Código do Boleto " + boleto.getId() + boleto.getCodeSlip()
			+ " / Valor: R$ " + boleto.getValueSlip() +
			" / Pago com Sucesso! ";
		}
		return "Erro ao pagar o boleto";
	}
	
	
	@GetMapping("/listContas")
	public ResponseEntity<List<Conta>> listAll(){
		return contaRequest.listContas();
	}
	
	@GetMapping("conta/{id}")
	public ResponseEntity<ContaDto> contaById(@PathVariable Long id){
		ContaDto contaEncontrada = contaRequest.findById(id);
		return ResponseEntity.ok(contaEncontrada);
	}
	
	@PostMapping("boleto")
	public ResponseEntity<PaymentSlip> postBoleto(
			@RequestBody PaymentSlipDto paymentDto) {
		ContaDto contaDto =
				contaRequest.findById(paymentDto.getIdClient());
		PaymentSlip paymentSlip =
				toEntity(paymentDto,contaDto);
		
		// Como setar o novo saldo após o pagamento do boleto
		contaDto.setSaldo(contaDto.getSaldo() - paymentSlip.getValueSlip());
		
		
		return new ResponseEntity(repository.save(paymentSlip),HttpStatus.OK);
	}
	
	
	private  PaymentSlip toEntity(PaymentSlipDto dto, ContaDto contaDto) {
		PaymentSlip boleto = new PaymentSlip();
		boleto.setIdClient(contaDto.getId());
		boleto.setCodeSlip(dto.getCodeSlip());
		boleto.setValueSlip(dto.getValueSlip());
		boleto.setStatus(dto.getStatus());
        return boleto; 
    }
		
}

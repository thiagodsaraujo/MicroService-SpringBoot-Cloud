package com.github.thg.bankapi.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.thg.bankapi.entities.Conta;
import com.github.thg.bankapi.entities.Extract;
import com.github.thg.bankapi.entities.enums.TipoOperacao;
import com.github.thg.bankapi.repositories.ContaRepository;
import com.github.thg.bankapi.repositories.ExtractRepository;
import com.github.thg.bankapi.util.model.DataUtil;

@Service
public class ExtractService extends GenericServiceImpl<Extract, Long>{

	@Autowired
	private ExtractRepository extractRepository;
	
	
	@Autowired
	private DataUtil dataUtil;
	
	@Autowired
	public ContaRepository contaRepository;
	
	public ExtractService(ExtractRepository extractRepository	) {
		super(extractRepository);
		this.extractRepository = extractRepository;
	}
	
//	public Extract generate1(Boolean credit, Conta conta, TipoOperacao tipo, Double value) {
//		
//		return generate(credit, conta, tipo, value);
//		
//	}
	
	public Extract generate(Boolean credit, Conta conta, TipoOperacao tipo,Double saldoAnterior,Double saldoAtual, Double value) {
		Calendar date = Calendar.getInstance();
		
		Extract extract = new Extract(date, tipo, saldoAnterior,saldoAtual, value, conta);
		extract.setInformation(createInformation(credit, conta, tipo, value, date));
		
		extract = extractRepository.save(extract);
		return extract;
	}
	
	public Extract generateSlip(Conta conta, TipoOperacao tipo,Double saldoAnterior,Double saldoAtual, Double value) {
		Calendar date = Calendar.getInstance();
		
		Extract extract = new Extract(date, tipo, saldoAnterior,saldoAtual, value, conta);
		extract.setInformation(createInformationSlip(conta, tipo, value, date));
		extract = extractRepository.save(extract);
		return extract;
	}
	
	
	private String createInformation(Boolean credit, Conta conta, TipoOperacao tipo, Double value, Calendar date) {
		if(credit) {
			return informationWithdraw(conta, tipo, value, date);
		} else {
			return informationDeposit(conta, tipo, value, date);
		}
	}
	
	private String createInformationSlip(Conta conta, TipoOperacao tipo, Double value, Calendar date) {
			return informationSlip(conta, tipo, value, date);
		
	}
	

	private String informationWithdraw(Conta conta, TipoOperacao tipo, Double value,Calendar date) {
		if(TipoOperacao.SAQUE.equals(tipo)) {
			return String.format("DATA: %s" + "\n"
			+ "SAQUE REALIZADO DE R$ %.2f\n" 
			+ "INFORMAÇÕES DO CLIENTE: %s %s, CONTA %s AG: %s", dataUtil.dataFormatada(date.getTime()), value, conta.getClient().getFirstName().split(" ")[0],
			conta.getClient().getLastName().split(" ")[0],conta.getNumber(), conta.getAgency().getNumber());
		} else {
			return String.format("DATA: %s" + "\n"
					+ "SAQUE DE R$ %.2f", dataUtil.dataFormatada(date.getTime()), value);
		}

	}
	
	private String informationDeposit(Conta conta, TipoOperacao tipo, Double value, Calendar date) {
		if(TipoOperacao.DEPOSITO.equals(tipo)) {
			return String.format("DATA: %s \n"
			+ "DEPOSITO REALIZADO DE R$ %.2f\n" 
			+ "INFORMAÇÕES DO CLIENTE: %s %s, CONTA %s AG: %s", dataUtil.dataFormatada(date.getTime()), value, conta.getClient().getFirstName().split(" ")[0],
			conta.getClient().getLastName().split(" ")[0],conta.getNumber(), conta.getAgency().getNumber());
		} else {
			return String.format("DATA: %s" + "\n" 
					+ "DEPÓSITO DE R$ %.2f", dataUtil.dataFormatada(date.getTime()), conta);
		}

	}
	
	private String informationSlip(Conta conta, TipoOperacao tipo, Double value, Calendar date) {
		if(TipoOperacao.PAGAMENTO.equals(tipo)) {
			return String.format("DATA: %s \n"
			+ "BOLETO PAGO COM SUCESSO! VALOR DE R$ %.2f\n" 
			+ "INFORMAÇÕES DO CLIENTE: %s %s, CONTA %s AG: %s", dataUtil.dataFormatada(date.getTime()), value, conta.getClient().getFirstName().split(" ")[0],
			conta.getClient().getLastName().split(" ")[0],conta.getNumber(), conta.getAgency().getNumber());
		} else {
			return "Erro ao gerar o extrato";
		}

	}
	
	public List<Extract> listAllByConta(Long id){
		Conta contaSearched = contaRepository.findById(id).get();
		
//		Conta conta = contaRepository.findById(id)
//				.orElseThrow(() -> new Exception("Conta not found for this id :" + id));
		
		List<Extract> listTemp = new ArrayList<>();
		List<Extract> listaExtract = extractRepository.findAll();
		for (Extract extract : listaExtract) {
			if (extract.getConta().getId() == contaSearched.getId()) {
				listTemp.add(extract);
			}
		}
		return listTemp; 
	}
	
	
	public Extract search(Long id, Long extractId) {
		return search(extractId);
	}

	@Override
	protected Extract updateData(Extract entity, Extract newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}

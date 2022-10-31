package com.github.thg.bankapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.thg.bankapi.entities.Conta;
import com.github.thg.bankapi.repositories.ContaRepository;
import com.github.thg.bankapi.services.exceptions.IntegrityDataException;
import com.github.thg.bankapi.services.exceptions.ObjectNotFoundException;
import com.github.thg.bankapi.services.exceptions.SaldoInsuficienteException;
import com.github.thg.bankapi.services.exceptions.ValorInvalidoException;

@Service
public class ContaService extends GenericServiceImpl<Conta,Long>{

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private ExtractService extractService;
	
	
	public ContaService(ContaRepository contaRepository) {
		super(contaRepository);
		this.contaRepository = contaRepository;
	}
	
	@Override
	public List<Conta> listAll(){
		return contaRepository.findAll();
	}
	
	@Transactional
	public Conta getPayment(Long id, Double valor) throws SaldoInsuficienteException {
		
		if (valor == null) {
			throw new ValorInvalidoException("Valor não pode ser zero");
		}
		
		Conta contaProcurada = search(id);
		
		if (contaProcurada == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName());
		}
		
		if (contaProcurada.getSaldo() < valor) {
			throw new SaldoInsuficienteException("Saldo Insuficiente! Saldo Atual: R$"
					+ contaProcurada.getSaldo() + ", Valor da Operação: R$ " + valor);
		}
		
		contaProcurada.setSaldo(contaProcurada.getSaldo() - valor);
		return contaRepository.save(contaProcurada);
	}
	
	
	
	@Override
	public Conta search(Long key) {
		Optional<Conta> conta = contaRepository.findById(key);
		return conta.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + key + ", Tipo: " + Conta.class.getName()));
	}
	
	@Override
	public Conta update(Conta novaConta) {
		Conta conta = search(novaConta.getId());
		novaConta = updateData(conta, novaConta);
		return contaRepository.save(novaConta);
	}
	
	@Override
	protected Conta updateData(Conta entity, Conta newEntity) {
		Conta conta = new Conta(newEntity.getNumber(), 
				newEntity.getSaldo());
		conta.setId(entity.getId());
		conta.setClient(entity.getClient());
		conta.setAgency(entity.getAgency());
		
		return conta;
		
	}
	
	@Override
	public void remove(Long key) {
		search(key);
		
		try {
			contaRepository.deleteById(key);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityDataException("Não é possivel excluir a conta que possui clientes!");
		}
	
}
}

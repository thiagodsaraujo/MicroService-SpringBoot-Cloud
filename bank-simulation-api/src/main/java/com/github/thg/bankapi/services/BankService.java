package com.github.thg.bankapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.thg.bankapi.entities.Bank;
import com.github.thg.bankapi.repositories.BankRepository;
import com.github.thg.bankapi.services.exceptions.IntegrityDataException;
import com.github.thg.bankapi.services.exceptions.ObjectNotFoundException;

@Service	
public class BankService extends GenericServiceImpl<Bank, Long>{
	
	@Autowired
	private BankRepository bankRepository;
	
	
	public BankService(BankRepository bankRepository) {
		super(bankRepository);
		this.bankRepository = bankRepository;
		
	}
	
	@Override
	public Bank search(Long key) {
		Optional<Bank> bank = bankRepository.findById(key);
		
		return bank.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + key + ", Tipo: " + Bank.class.getName()));
	}

	@Override
	public Bank update(Bank newBank) {
		Bank bank = search(newBank.getId());
		newBank = updateData(bank, newBank);
		return super.update(newBank);
	}
	
	@Override
	protected Bank updateData(Bank entity, Bank newEntity) {
		Bank bankUpdated = new Bank();
		
		bankUpdated.setId(entity.getId());
		bankUpdated.setName(newEntity.getName());
		return bankUpdated;
	}

	@Override
	public void remove(Long key) {
		search(key);
		
		try {
			bankRepository.deleteById(key);
		} catch (DataIntegrityViolationException e){
			throw new IntegrityDataException("Não é possível excluir um banco que possui agencias!");
			
		}
		
	}
		


}

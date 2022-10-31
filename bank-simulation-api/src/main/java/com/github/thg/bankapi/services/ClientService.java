package com.github.thg.bankapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.thg.bankapi.entities.Client;
import com.github.thg.bankapi.repositories.ClientRepository;
import com.github.thg.bankapi.services.exceptions.IntegrityDataException;
import com.github.thg.bankapi.services.exceptions.ObjectNotFoundException;

@Service	
public class ClientService extends GenericServiceImpl<Client, Long>{
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	public ClientService(ClientRepository clientRepository) {
		super(clientRepository);
		this.clientRepository = clientRepository;
		
	}
	

	
	@Override
	public Client search(Long key) {
		Optional<Client> bank = clientRepository.findById(key);
		
		return bank.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + key + ", Tipo: " + Client.class.getName()));
	}
	

	@Override
	public Client update(Client newBank) {
		Client bank = search(newBank.getId());
		newBank = updateData(bank, newBank);
		return super.update(newBank);
	}
	
	@Override
	protected Client updateData(Client entity, Client newEntity) {
		//TODO
		return null;
	}

	@Override
	public void remove(Long key) {
		search(key);
		
		try {
			clientRepository.deleteById(key);
		} catch (DataIntegrityViolationException e){
			throw new IntegrityDataException("Não é possível excluir um cliente cadastrado que possui agencias!");
			
		}
		
	}
		


}

package com.github.thg.bankapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.thg.bankapi.entities.Agency;
import com.github.thg.bankapi.repositories.AgencyRepository;
import com.github.thg.bankapi.services.exceptions.IntegrityDataException;
import com.github.thg.bankapi.services.exceptions.ObjectNotFoundException;

@Service	
public class AgencyService extends GenericServiceImpl<Agency, Long>{
	
	@Autowired
	private AgencyRepository agencyRepository;
	
	
	public AgencyService(AgencyRepository agencyRepository) {
		super(agencyRepository);
		this.agencyRepository = agencyRepository;
		
	}
	
	@Override
	public Agency search(Long key) {
		Optional<Agency> agency = agencyRepository.findById(key);
		
		return agency.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + key + ", Tipo: " + Agency.class.getName()));
	}

	@Override
	public Agency update(Agency newAgency) {
		Agency agency = search(newAgency.getId());
		newAgency = updateData(agency, newAgency);
		return agencyRepository.save(newAgency);
	}
	
	@Override
	protected Agency updateData(Agency entity, Agency newEntity) {
		Agency agency = new Agency(newEntity.getNumber(), newEntity.getName());
		agency.setId(entity.getId());
		agency.setBank(entity.getBank());
		return agency;
	}
	
//	public Agency searchByNumber(Long bankId, String number) {
//		Optional<Agency> agency = agencyRepository.searchByNumber(bankId, number);
//		
//		return agency.orElseThrow(() -> new ObjetoNaoEncontradoException(
//				"Agência não encontrada! Numero: " + number));
//	}
	
//	public List<Agency> searchByBank(Long id){
//		return agencyRepository.searchByNumber(id);
//	}

	@Override
	public void remove(Long key) {
		search(key);
		
		try {
			agencyRepository.deleteById(key);
		} catch (DataIntegrityViolationException e){
			throw new IntegrityDataException("Não é possível excluir a agencia que possui contas!");
			
		}
		
	}
	
	
		


}

package com.github.thg.bankapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.github.thg.bankapi.entities.Bank;
import com.github.thg.bankapi.repositories.BankRepository;
import com.github.thg.bankapi.services.AgencyService;
import com.github.thg.bankapi.services.BankService;

@RestController
@RequestMapping("/bank/banks")
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	AgencyService agencyService;
	
	
	@GetMapping("/listBanks")
	public ResponseEntity<List<Bank>> listAll(){
		List<Bank> banks = bankService.listAll();
		return ResponseEntity.ok(banks);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Bank> findById(@PathVariable Long id) {
		Bank bankSearched = bankService.search(id);
		if(bankSearched == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bankSearched);
	}
	
	
	
	@PostMapping(value = "/createBank")
	public ResponseEntity<Bank> createBank(@Validated @RequestBody Bank bank){
		final Bank updateBank = bankRepository.save(bank);
		return ResponseEntity.ok(updateBank);
		
	}
	
	@PostMapping(value = "/addBank")
	public ResponseEntity<Void> addBank(@Validated @RequestBody Bank bank){
		Bank registredBank = bankService.save(bank);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registredBank.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateBank(@PathVariable Long id, @Validated @RequestBody Bank newBank){
		Bank bankSearched = bankService.update(newBank);
		bankSearched.setId(id);
		bankSearched = bankService.update(newBank);
		return ResponseEntity.noContent().build();
	}
	

	
	@PutMapping(value = "/updateAgency/{id}")
	public ResponseEntity<Bank> updateBankById(@PathVariable Long id, @RequestBody Bank bankDetails)
	throws Exception {
		Bank bank = bankRepository.findById(id)
				.orElseThrow(() -> new Exception("Agency not found for this id :" + id));
		
//		agency.setId(agencyDetails.getId());
		bank.setName(bankDetails.getName());
		
		
		final Bank updateBank = bankRepository.save(bank);
		
		return ResponseEntity.ok(updateBank);

	}
	
//	@GetMapping("/{id}/agencia/{number}")
//	public ResponseEntity<Agency> searchAgency(@PathVariable Long id, @PathVariable String number){
//		Agency searchedAgency = agencyService.searchByNumber(id, number);
//		
//		if (searchedAgency == null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(searchedAgency);
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		bankService.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id){
		bankService.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	
	


}

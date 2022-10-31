package com.github.thg.bankapi.controller;

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

import com.github.thg.bankapi.entities.Agency;
import com.github.thg.bankapi.repositories.AgencyRepository;
import com.github.thg.bankapi.services.AgencyService;

@RestController
@RequestMapping("/bank/agency")
public class AgencyController {
	
	@Autowired
	AgencyRepository repository;
	
	@Autowired
	AgencyService agencyService;
	
	
	
//	@GetMapping("/listAgencys")
//	public ResponseEntity<List<Agency>> findAll(){
//		List<Agency> list = repository.findAll();
//		return ResponseEntity.ok(list);
//	}
	
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<Agency> findById(@PathVariable Long id) {
//		
//		
//		Agency obj = repository.findById(id).get();
//		return ResponseEntity.ok(obj);
//	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id){
//		repository.deleteById(id);
//		return ResponseEntity.ok().build();
//		
//	}
	
	@GetMapping("/listAgencys")
	public ResponseEntity<List<Agency>> listAll(){
		List<Agency> agencys = agencyService.listAll();
		return ResponseEntity.ok(agencys);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Agency> findById(@PathVariable Long id) {
		Agency agencySearched = agencyService.search(id);
		if(agencySearched == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(agencySearched);
	}
	
	
	
	@PostMapping(value = "/createAgency")
	public ResponseEntity<Agency> createAgency(@Validated @RequestBody Agency agency){
		final Agency updateAgency = repository.save(agency);
		return ResponseEntity.ok(updateAgency);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateAgency(@PathVariable Long id, @Validated @RequestBody Agency newAgency){
		Agency agencySearched = agencyService.update(newAgency);
		agencySearched.setId(id);
		agencySearched = agencyService.update(newAgency);
		return ResponseEntity.noContent().build();
	}
	

	
	@PutMapping(value = "/updateAgency/{id}")
	public ResponseEntity<Agency> updateAgencyById(@PathVariable Long id, @RequestBody Agency agencyDetails)
	throws Exception {
		Agency agency = repository.findById(id)
				.orElseThrow(() -> new Exception("Agency not found for this id :" + id));
		
//		agency.setId(agencyDetails.getId());
		agency.setNumber(agencyDetails.getNumber());
		agency.setName(agencyDetails.getName());
		agency.setBank(agencyDetails.getBank());
		
		
		final Agency updateAgency = repository.save(agency);
		
		return ResponseEntity.ok(updateAgency);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		agencyService.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	


}

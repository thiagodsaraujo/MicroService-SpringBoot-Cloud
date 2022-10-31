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

import com.github.thg.bankapi.entities.Client;
import com.github.thg.bankapi.repositories.ClientRepository;
import com.github.thg.bankapi.services.ClientService;

@RestController
@RequestMapping("/bank/client")
public class ClientController {
	
	@Autowired
	ClientRepository repository;
	
	@Autowired
	private ClientService clientService;
	
	
	
//	@GetMapping("/listClients")
//	public ResponseEntity<List<Client>> findAll(){
//		List<Client> list = repository.findAll();
//		return ResponseEntity.ok(list);
//	}
	
	@GetMapping("/listClients")
	public ResponseEntity<List<Client>> listAll(){
		List<Client> clients = clientService.listAll();
		return ResponseEntity.ok(clients);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> searchById(@PathVariable Long id){
		Client clientSearched = clientService.search(id);
		if(clientSearched == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientSearched);
	}
	
	@PostMapping(value = "/createclient")
	public ResponseEntity<Client> createClient(@Validated @RequestBody Client client){
		final Client updateClient = repository.save(client);
		return ResponseEntity.ok(updateClient);
		
	}
	
	// nao funciona dessa forma
	@PutMapping("/updateClient2/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @Validated @RequestBody Client newClient){
		Client clientSearched = clientService.search(id);
		clientSearched.setId(id);
		clientSearched = clientService.update(clientSearched);
		
		return ResponseEntity.noContent().build();
	}

	
	@PutMapping(value = "/updateClient/{id}")
	public ResponseEntity<Client> updateClientById(@PathVariable Long id, @RequestBody Client clientDetails)
	throws Exception {
		Client client = repository.findById(id)
				.orElseThrow(() -> new Exception("Client not found for this id :" + id));
		
//		client.setId(clientDetails.getId());
		client.setFirstName(clientDetails.getFirstName());
		client.setLastName(clientDetails.getLastName());
		client.setCpf(clientDetails.getCpf());
		client.setEmail(clientDetails.getEmail());
		client.setSecret(clientDetails.getSecret());
		
		final Client updateClient = repository.save(client);
		
		return ResponseEntity.ok(updateClient);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		repository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id){
		clientService.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	
	

}

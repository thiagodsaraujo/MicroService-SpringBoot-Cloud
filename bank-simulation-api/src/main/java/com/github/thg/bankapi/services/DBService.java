/**
 * 
 */
package com.github.thg.bankapi.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.thg.bankapi.entities.Agency;
import com.github.thg.bankapi.entities.Bank;
import com.github.thg.bankapi.entities.Client;
import com.github.thg.bankapi.entities.Conta;



@Service
public class DBService {
	
	@Autowired
	private ContaService contaService;
	@Autowired
	private AgencyService agencyService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private BankService bankService;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;


	public void instantiateTestDatabase() {
		
		Bank b1 = new Bank("Banco do Brasil");
		Bank b2 = new Bank("Caixa Econômica Federal");
		Bank b3 = new Bank("Bradesco");
		Bank b4 = new Bank("NuConta");

		
		bankService.saveAll(Arrays.asList(b1, b2, b3, b4));



		Agency b1_ag1 = new Agency("0001-1", "Banco do Brasil Centro", b1);
		Agency b1_ag2 = new Agency("0002-1", "Banco do Brasil Sul", b1);

		Agency b2_ag1 = new Agency("0001-1", "Caixa Av. Miguel Rosa", b2);
		Agency b2_ag2 = new Agency("0002-1", "Caixa Av. Frei Serafim", b2);

		agencyService.saveAll(Arrays.asList(b1_ag1, b1_ag2, b2_ag1, b2_ag2));

		Client cliente = new Client("Gleison",  "Andrade","756.401.413-01", "123456");
		Client cliente2 = new Client("Francisco",  "Andrade", "672.281.173-52","123456");
		
		Client admin = new Client("Admin","1", "952.797.143-80", "123456");

		clientService.saveAll(Arrays.asList(cliente, cliente2, admin));

		Conta conta = new Conta("00001-0", 0.0, cliente, b1_ag1);
		Conta conta2 = new Conta("00002-0", 0.0, cliente2, b2_ag1);

		contaService.saveAll(Arrays.asList(conta, conta2));
	}

}

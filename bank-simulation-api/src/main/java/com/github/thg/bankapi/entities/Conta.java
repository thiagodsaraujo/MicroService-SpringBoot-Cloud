package com.github.thg.bankapi.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Conta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	private Double saldo = 0.0;
	
	@ManyToOne
	@JoinColumn(name= "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name= "agency_id")
	private Agency agency;
	
	
	public Conta() {
		
	}
	
	public Conta(Long id, String number, Double saldo) {
		super();
		this.id = id;
		this.number = number;
		this.saldo = saldo;
	}

	public Conta(String number, Double saldo) {
		super();
		this.number = number;
		this.saldo = saldo;
	}
	
	public Conta(String numero, Double saldo, Client client, Agency agency) {
		super();
		this.number = numero;
		this.saldo = saldo;
		this.client = client;
		this.agency = agency;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public Double getSaldo() {
		return saldo;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Agency getAgency() {
		return agency;
	}


	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agency, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(agency, other.agency) && Objects.equals(number, other.number);
	}
	
	
	
	

}

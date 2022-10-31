package com.github.thg.bankapi.entities;

import lombok.Data;

@Data
public class Conta {

	private Long id;
	private String number;
	private Double saldo;
	
	
	public Conta() {
		super();
	}


	public Conta(String number, Double saldo) {
		super();
		this.number = number;
		this.saldo = saldo;
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
	
	
	
}

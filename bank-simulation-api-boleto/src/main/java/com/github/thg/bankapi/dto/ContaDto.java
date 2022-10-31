package com.github.thg.bankapi.dto;

import lombok.Data;

@Data
public class ContaDto {

	private long id;
	
	private Double saldo;
	

	public ContaDto() {
		super();
	}
	

	public ContaDto(Double saldo) {
		super();
		this.saldo = saldo;
	}


	public ContaDto(long id, Double saldo) {
		super();
		this.id = id;
		this.saldo = saldo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
}

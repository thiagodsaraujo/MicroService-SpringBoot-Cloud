package com.github.thg.bankapi.enun;

public enum EnumStatus {
	PAGO("PAGO"), PENDENTE("PENDENTE");
	
	private String descricao;
	
	EnumStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

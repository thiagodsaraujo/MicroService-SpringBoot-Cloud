package com.github.thg.bankapi.entities.enums;

public enum TipoOperacao {
	
DEPOSITO("Deposito"), SAQUE("Saque"), PAGAMENTO("Pagamento");
	
	private String descricao;
	
	TipoOperacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

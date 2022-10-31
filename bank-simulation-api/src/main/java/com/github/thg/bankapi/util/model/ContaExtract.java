package com.github.thg.bankapi.util.model;

import java.io.Serializable;

public class ContaExtract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long contaId;
	private Long extractId;
	
	public ContaExtract(Long contaId, Long extractId) {
		super();
		this.contaId = contaId;
		this.extractId = extractId;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public Long getExtractId() {
		return extractId;
	}

	public void setExtractId(Long extractId) {
		this.extractId = extractId;
	}
	
	

}

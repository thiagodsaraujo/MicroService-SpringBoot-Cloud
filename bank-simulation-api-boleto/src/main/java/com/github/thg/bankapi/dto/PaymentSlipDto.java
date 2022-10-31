package com.github.thg.bankapi.dto;

import com.github.thg.bankapi.enun.EnumStatus;

public class PaymentSlipDto {
	
	
	private String codeSlip;
	
	private EnumStatus status;
	
	private Double valueSlip;
	
	private Long idClient;
	

	public PaymentSlipDto(String codeSlip, EnumStatus status, Double valueSlip, Long idClient) {
		super();
		this.codeSlip = codeSlip;
		this.status = status;
		this.valueSlip = valueSlip;
		this.idClient = idClient;
	}
	
	

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}


	public String getCodeSlip() {
		return codeSlip;
	}

	public void setCodeSlip(String codeSlip) {
		this.codeSlip = codeSlip;
	}

	public Double getValueSlip() {
		return valueSlip;
	}

	public void setValueSlip(Double valueSlip) {
		this.valueSlip = valueSlip;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	
	

	// TODO: Colocar data de vencimento e geração do boleto
	// TODO: Na hora de pagar verificar e gerar multa ou não caso vencimento
	
	
	

}

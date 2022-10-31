package com.github.thg.bankapi.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.github.thg.bankapi.dto.ContaDto;
import com.github.thg.bankapi.enun.EnumStatus;

@Entity
public class PaymentSlip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codeSlip;
	
	@Enumerated(EnumType.STRING)
	private EnumStatus status;

	private Double valueSlip;
	
	
	private Long idCliente;
	
	

	public PaymentSlip(String codeSlip, EnumStatus status, Double valueSlip, Long idClient) {
		super();
		this.codeSlip = codeSlip;
		this.status = status;
		this.valueSlip = valueSlip;
		this.idCliente = idClient;
	}


	public PaymentSlip(EnumStatus status, Double valueSlip, Long idClient) {
		super();
		this.status = status;
		this.valueSlip = valueSlip;
		this.idCliente = idClient;
	}

	public PaymentSlip(String codeSlip, Double valueSlip, Long idClient) {
		super();
		this.codeSlip = codeSlip;
		this.valueSlip = valueSlip;
		this.idCliente = idClient;
	}
	
	

	public PaymentSlip() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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

//	public Boolean getPaid() {
//		return paid;
//	}
//
//	public void setPaid(Boolean paid) {
//		this.paid = paid;
//	}

	public Double getValueSlip() {
		return valueSlip;
	}

	public Double setValueSlip(Double valueSlip) {
		return this.valueSlip = valueSlip;
	}

	public Long getIdClient() {
		return idCliente;
	}

	public void setIdClient(Long idClient) {
		this.idCliente = idClient;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentSlip other = (PaymentSlip) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "PaymentSlip [id=" + id + ", codeSlip=" + codeSlip + ", status=" + status + ", valueSlip=" + valueSlip
				+ ", idCliente=" + idCliente + "]";
	}
	
	
	

	// TODO: Colocar data de vencimento e geração do boleto
	// TODO: Na hora de pagar verificar e gerar multa ou não caso vencimento
	
	
	

}

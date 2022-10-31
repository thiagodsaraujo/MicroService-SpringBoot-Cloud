package com.github.thg.bankapi.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.thg.bankapi.entities.enums.TipoOperacao;


@Entity
public class Extract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateExtract;
	
	@Enumerated(EnumType.STRING)
	private TipoOperacao tipoOperacao;
	
	private Double saldoAnterior;
	private Double saldoAtual;
	
	private Double valor;
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}


	private String information;

	@ManyToOne
	private Conta conta;

	
	public Extract() {
		
	}
	
	public Extract(Calendar dateExtract, TipoOperacao tipoOperacao, Double saldoAnterior, Double saldoAtual, Double valor, Conta conta) {
		super();
		this.dateExtract = dateExtract;
		this.tipoOperacao = tipoOperacao;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
		this.valor = valor;
		this.conta = conta;
	}
	
	public Extract(Calendar dateExtract, TipoOperacao tipoOperacao, Double value, Conta conta) {
		super();
		this.dateExtract = dateExtract;
		this.tipoOperacao = tipoOperacao;
		this.valor = value;
		this.conta = conta;
	}


	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	

	public Calendar getDateExtract() {
		return dateExtract;
	}

	public void setDateExtract(Calendar dateExtract) {
		this.dateExtract = dateExtract;
	}


	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}


	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}



	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}



	public Double getSaldoAnterior() {
		return saldoAnterior;
	}



	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}



	public Double getSaldoAtual() {
		return saldoAtual;
	}



	public void setSaldoAtual(Double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}



	public Conta getConta() {
		return conta;
	}



	public void setConta(Conta conta) {
		this.conta = conta;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extract other = (Extract) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Extract [id=" + id + ", "
				+ "dateExtract=" + dateExtract + ","
				+ " tipoOperacao=" + tipoOperacao
				+ ", SaldoAnterior=" + saldoAnterior + ","
				+ " SaldoAtual=" + saldoAtual + ","
				+ " valor=" + valor
				+ ", information=" + information + ","
				+ " conta=" + conta.getClient().getFirstName().split(" ")[0] + "]";
	}
	
	
	
	

}

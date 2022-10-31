package com.github.thg.bankapi.service.exceptions;

public class ErrorServerException extends Exception {

private static final long serialVersionUID = 1L;
	
	public ErrorServerException(String mensagem) {
		super(mensagem);
	}
	
	public ErrorServerException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}

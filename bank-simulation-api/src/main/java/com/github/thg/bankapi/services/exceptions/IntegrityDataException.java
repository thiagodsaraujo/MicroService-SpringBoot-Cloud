/**
 * 
 */
package com.github.thg.bankapi.services.exceptions;


public class IntegrityDataException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IntegrityDataException(String mensagem) {
		super(mensagem);
	}
	
	public IntegrityDataException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}

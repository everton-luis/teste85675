package com.treasy.dev.services.exceptions;

public class IdNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2403364120468957255L;
	
	public IdNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public IdNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}

}

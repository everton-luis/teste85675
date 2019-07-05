package com.treasy.dev.services.exceptions;

public class ParentIdNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6745168716974753050L;

	public ParentIdNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ParentIdNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
	
}

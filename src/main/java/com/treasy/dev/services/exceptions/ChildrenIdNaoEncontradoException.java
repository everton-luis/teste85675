package com.treasy.dev.services.exceptions;

public class ChildrenIdNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3324866768564816320L;

	public ChildrenIdNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ChildrenIdNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
	
}

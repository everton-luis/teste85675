package com.treasy.dev.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.treasy.dev.domain.DetalhesErro;
import com.treasy.dev.services.exceptions.ParentIdNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ParentIdNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleParentIdNaoEncontradoException
	(ParentIdNaoEncontradoException e,HttpServletRequest request){
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("o parentId não pode ser encontrado");
		erro.setMensagemDesenvolvedor("entrar em contato com o suporte");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
}

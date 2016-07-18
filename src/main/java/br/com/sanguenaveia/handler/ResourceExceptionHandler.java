package br.com.sanguenaveia.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.sanguenaveia.domain.dto.Erro;
import br.com.sanguenaveia.service.exception.DoadorNaoEncontradoException;
import br.com.sanguenaveia.service.exception.IdentificadorNullException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(DoadorNaoEncontradoException.class)
	public ResponseEntity<Erro> handleDoadorNaoEncontradoException(
			DoadorNaoEncontradoException e, HttpServletRequest request) {
		
		Erro erro = new Erro();
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setTitulo("O doador não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.sanguenaveia.com.br/404");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);		
	}

	@ExceptionHandler(IdentificadorNullException.class)
	public ResponseEntity<Erro> handleIdentificadorNullException(
			IdentificadorNullException e, HttpServletRequest request) {
		
		Erro erro = new Erro();
		erro.setStatus(HttpStatus.BAD_REQUEST.value());
		erro.setTitulo("O ID não pode ser nulo");
		erro.setMensagemDesenvolvedor("http://erros.sanguenaveia.com.br/400");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}

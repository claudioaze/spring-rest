package br.com.sanguenaveia.service.exception;

public class IdentificadorNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdentificadorNullException(String mensagem) {
		super(mensagem);
	}
	
	public IdentificadorNullException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}

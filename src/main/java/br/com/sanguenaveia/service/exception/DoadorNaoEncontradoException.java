package br.com.sanguenaveia.service.exception;

public class DoadorNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DoadorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public DoadorNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}

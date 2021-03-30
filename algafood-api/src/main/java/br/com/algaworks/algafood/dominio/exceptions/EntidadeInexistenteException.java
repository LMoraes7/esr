package br.com.algaworks.algafood.dominio.exceptions;

public class EntidadeInexistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeInexistenteException(String msg) {
		super(msg);
	}
}

package br.com.algaworks.algafood.dominio.exceptions;

public class EntidadeEmUsoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String msg) {
		super(msg);
	}
}

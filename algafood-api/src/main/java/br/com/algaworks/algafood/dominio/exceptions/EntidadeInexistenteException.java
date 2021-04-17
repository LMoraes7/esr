package br.com.algaworks.algafood.dominio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)// reason = "Entidade não encontrada")
public class EntidadeInexistenteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeInexistenteException(String msg) {
		super(msg);
	}
}

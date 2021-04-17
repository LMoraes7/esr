package br.com.algaworks.algafood.dominio.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
import br.com.algaworks.algafood.dominio.exceptions.NegocioException;
import br.com.algaworks.algafood.dominio.modelo.Modelo;

@Component
public class ConsultarOuFalhar {
	
//	Classe que possui a função de isolar códigos repetidos nas Classes dominio.service

	public Modelo consultarPorId(Optional<? extends Modelo> modeloOptional) {
		if(modeloOptional.isPresent())
			return modeloOptional.get();
		throw new EntidadeInexistenteException("Entidade Inexistente!");
	}
	
	public Modelo consultarPorIdParaValidarRequisicao(Optional<? extends Modelo> modeloOptional) {
		if(modeloOptional.isPresent())
			return modeloOptional.get();
		throw new NegocioException("Entidade informada no corpo da requisição é inexistente!");
	}
}

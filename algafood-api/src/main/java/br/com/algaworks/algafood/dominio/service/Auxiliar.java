package br.com.algaworks.algafood.dominio.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
import br.com.algaworks.algafood.dominio.modelo.Modelo;

@Component
public class Auxiliar {

	public Modelo consultarPorId(Optional<? extends Modelo> modeloOptional) {
		if(modeloOptional.isPresent())
			return modeloOptional.get();
		throw new EntidadeInexistenteException("Entidade Inexistente!");
	}
	
	public void deletarPorId(Optional<? extends Modelo> modeloOptional) {
		if(modeloOptional.isPresent()) {
			System.out.println("Entidade a ser deletada existe no BD");
			return;
		}
		throw new EntidadeInexistenteException("Entidade inexistente!"); 
	}
}

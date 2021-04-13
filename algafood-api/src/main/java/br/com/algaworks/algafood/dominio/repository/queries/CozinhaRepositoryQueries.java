package br.com.algaworks.algafood.dominio.repository.queries;

import java.util.List;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;

public interface CozinhaRepositoryQueries {

	List<Cozinha> comNomeSemelhante(String nome);
}

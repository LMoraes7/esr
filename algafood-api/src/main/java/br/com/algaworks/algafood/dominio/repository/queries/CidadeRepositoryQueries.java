package br.com.algaworks.algafood.dominio.repository.queries;

import java.util.List;

import br.com.algaworks.algafood.dominio.modelo.Cidade;

public interface CidadeRepositoryQueries {

	List<Cidade> comNomeSemelhante(String nome);
	
//	Optional<Cidade> consultarPorId(Long id);
}

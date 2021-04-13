package br.com.algaworks.algafood.infraestrutura.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;
import br.com.algaworks.algafood.dominio.repository.CozinhaRepository;
import br.com.algaworks.algafood.dominio.repository.queries.CozinhaRepositoryQueries;
import br.com.algaworks.algafood.infraestrutura.repository.spec.CozinhaSpecs;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepositoryQueries{

	@Autowired
	@Lazy
	private CozinhaRepository repository;
	
	@Override
	public List<Cozinha> comNomeSemelhante(String nome) {
		return repository.findAll(CozinhaSpecs.comNomeSemelhante(nome));
	}

}

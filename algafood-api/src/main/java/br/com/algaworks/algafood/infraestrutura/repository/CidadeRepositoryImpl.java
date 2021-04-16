package br.com.algaworks.algafood.infraestrutura.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Cidade;
import br.com.algaworks.algafood.dominio.repository.CidadeRepository;
import br.com.algaworks.algafood.dominio.repository.queries.CidadeRepositoryQueries;
import br.com.algaworks.algafood.infraestrutura.repository.spec.CidadeSpecs;

@Repository
public class CidadeRepositoryImpl implements CidadeRepositoryQueries{

	@Autowired
	@Lazy
	private CidadeRepository repository;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> comNomeSemelhante(String nome) {
		return this.repository.findAll(CidadeSpecs.comNomeSemelhante(nome));
	}

//	@Override
//	public Optional<Cidade> consultarPorId(Long id) {
//		String jpql = "SELECT c FROM Cidade c JOIN c.estado WHERE c.id = :id";
//		Cidade cidade = manager.createQuery(jpql, Cidade.class)
//		.setParameter("id", id)
//		.getSingleResult();
//		return Optional.ofNullable(cidade);
//	}
}
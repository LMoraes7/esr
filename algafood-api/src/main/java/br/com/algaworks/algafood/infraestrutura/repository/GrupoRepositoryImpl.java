package br.com.algaworks.algafood.infraestrutura.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.repository.queries.GrupoRepositoryQueries;

@Repository
public class GrupoRepositoryImpl implements GrupoRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;

//	@Override
//	public Optional<Grupo> consultarPorId(Long id) {
//		String jpql = "SELECT g FROM Grupo g JOIN FETCH g.permissoes WHERE g.id = :id";
//		Grupo grupo = manager.createQuery(jpql, Grupo.class)
//		.setParameter("id", id)
//		.getSingleResult();
//		return Optional.ofNullable(grupo);
//	}

}

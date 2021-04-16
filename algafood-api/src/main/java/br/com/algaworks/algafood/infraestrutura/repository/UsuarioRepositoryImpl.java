package br.com.algaworks.algafood.infraestrutura.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.repository.queries.UsuarioRepositoryQueries;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
//	@Override
//	public Optional<Usuario> consultarPorId(Long id) {
//		String jpql = "SELECT u FROM Usuario u JOIN FETCH u.grupos WHERE u.id = :id";
//		Usuario usuario = manager.createQuery(jpql, Usuario.class)
//		.setParameter("id", id)
//		.getSingleResult();
//		return Optional.ofNullable(usuario);
//	}

}

package br.com.algaworks.algafood.dominio.infraestrutura.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		var jpql = "SELECT r FROM Restaurante r WHERE r.nome LIKE :nome AND "
				+ "r.taxaFrete BETWEEN :taxaFreteInicial AND :taxaFreteFinal";
		
		return manager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%"+nome+"%")
				.setParameter("taxaFreteInicial", taxaFreteInicial)
				.setParameter("taxaFreteFinal", taxaFreteFinal)
				.getResultList();
	}
}

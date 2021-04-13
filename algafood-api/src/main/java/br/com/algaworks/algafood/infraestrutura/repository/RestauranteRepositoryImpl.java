package br.com.algaworks.algafood.infraestrutura.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.RestauranteRepository;
import br.com.algaworks.algafood.dominio.repository.queries.RestauranteRepositoryQueries;
import br.com.algaworks.algafood.infraestrutura.repository.spec.RestauranteSpecs;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
//	A anotação @Lazy anotação informa que o Spring só irá 
//		injetar essa dependência quando ela for utilizada de fato
	@Autowired 
	@Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
//		A Interface CriteriaBuilder é responsável por criar as cláusulas JPQL -> between, equal,
//			like, ...
//		O EntityManager fornece uma instância de um CriteriaBuilder
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
//		A interface CriteriaQuery é responsável por criar a estrutura de uma query, a composição das cláusulas
//		Desejo uma instância de uma CriteriaQuery para eu realizar uma consulta de Restaurante
		CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
		
// 		O método .from(Restaurante.class) cria a cláusula -> FROM Restaurante r
		Root<Restaurante> root = criteriaQuery.from(Restaurante.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
	//		O método .like(root.get("nome"), "%"+nome+"%") cria a cláusula -> r.nome LIKE %nome%
	//		O root.get("nome") referencia o atributo nome da entidade Restaurante
			predicates.add(criteriaBuilder.like(root.get("nome"), "%"+nome+"%"));
		}
			
		if(taxaFreteInicial != null) {
	//		O método .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial) cria a cláusula -> r.taxaFrete >= taxaFreteInicial
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}

		if(taxaFreteFinal != null) {
	//		O método .lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal) cria a cláusula -> r.taxaFrete <= taxaFreteInicial
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
			
//		O método .where() -> adiciona a cláusula WHERE
//		Quando colocamos um Array de Predicate no método .where(), será feita uma cláusula JPQL usando o operador AND entre essas cláusulas
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
			
//		O método .createQuery(criteriaQuery) -> cria a query completa JPQL com as clásulas que definimos antes
//		SELECT r FROM Restaurante r WHERE r.nome LIKE %nome% AND r.taxaFrete >= taxaInicial AND r.taxaFrete <= taxaFinal
		TypedQuery<Restaurante> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public List<Restaurante> comFreteGratis() {
//		Consulta em JPQL
//		String jpql = "SELECT r FROM Restaurante r WHERE r.taxaFrete = 0";
//		return manager.createQuery(jpql, Restaurante.class).getResultList();

//		Usando CriteriaAPI
//		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//		CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
		
//		FROM Restaurante r
//		Root<Restaurante> root = criteriaQuery.from(Restaurante.class);
		
//		r.taxaFrete = 0
//		Predicate predicate = criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
		
//		WHERE r.taxaFrete = 0
//		criteriaQuery.where(predicate);
		
//		SELECT r FROM Restaurante r WHERE r.taxaFrete = 0
//		return manager.createQuery(criteriaQuery).getResultList();
		
		return this.restauranteRepository.findAll(RestauranteSpecs.comFreteGratis());
	}

	@Override
	public List<Restaurante> comNomeSemelhante(String nome) {
//		Consulta em JPQL
//		String jpql = "SELECT r FROM Restaurante r WHERE r.nome LIKE :nome";
//		return manager.createQuery(jpql, Restaurante.class).setParameter("nome", "%"+nome+"%").getResultList();
		
//		Usando CriteriaAPI
//		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//		CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
		
//		FROM Restaurante r
//		Root<Restaurante> root = criteriaQuery.from(Restaurante.class);
		
//		r.nome LIKE %nome%
//		Predicate predicate = criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
		
//		WHERE r.nome LIKE %nome%
//		criteriaQuery.where(predicate);
		
//		SELECT r FROM Restaurante r WHERE r.nome LIKE %nome%
//		return manager.createQuery(criteriaQuery).getResultList();
		
		return this.restauranteRepository.findAll(RestauranteSpecs.comNomeSemelhante(nome));
	}
}
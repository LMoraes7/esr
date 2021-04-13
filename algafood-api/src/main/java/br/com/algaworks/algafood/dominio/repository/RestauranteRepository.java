package br.com.algaworks.algafood.dominio.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.queries.RestauranteRepositoryQueries;

@Repository
public interface RestauranteRepository 
		extends CustomJpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante>, 
				RestauranteRepositoryQueries{
	
//	SELECT r FROM Restaurante r WHERE r.taxaFrete BETWENN taxaInicial AND taxaFinal
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
//	SELECT r FROM Restaurante r JOIN FETCH r.cozinha c WHERE c.id = :id  
	List<Restaurante> readByCozinha_Id(Long id);
	
//	SELECT COUNT(r) FROM Restaurante r JOIN r.cozinha c WHERE c.id = :id 
	int countByCozinha_Id(Long id);
	
//	Está no arquivo orm.xml
	List<Restaurante> consultarPorNome(@Param("nome") String nome);
	
	boolean existsByNome(String nome);
}
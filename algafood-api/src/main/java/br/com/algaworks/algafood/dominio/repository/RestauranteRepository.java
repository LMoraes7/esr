package br.com.algaworks.algafood.dominio.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries{
	
//	SELECT r FROM Restaurante r WHERE r.taxaFrete BETWENN :taxaInicial AND :taxaFinal
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
//	SELECT r FROM Restaurante r LEFT JOIN FETCH r.cozinha c WHERE c.id = :id  
	List<Restaurante> readByCozinha_Id(Long id);
	
//	SELECT count(r) FROM Restaurante r LEFT JOIN FETCH r.cozinha c WHERE c.id = :id
	int countByCozinha_Id(Long id);
	
//	Está no arquivo orm.xml
	List<Restaurante> consultarPorNome(@Param("nome") String nome);
	
	boolean existsByNome(String nome);
}
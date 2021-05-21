package br.com.algaworks.algafood.dominio.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, 
	JpaSpecificationExecutor<Restaurante>{

	@Query("SELECT r FROM Restaurante r WHERE r.taxaFrete = :taxaFrete AND r.nome LIKE %:nome%")
	List<Restaurante> findByTaxaFreteAndNomeContaining(BigDecimal taxaFrete, String nome);
}

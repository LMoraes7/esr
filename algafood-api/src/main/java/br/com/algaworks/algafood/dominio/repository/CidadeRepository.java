package br.com.algaworks.algafood.dominio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Cidade;
import br.com.algaworks.algafood.dominio.repository.queries.CidadeRepositoryQueries;

@Repository
public interface CidadeRepository extends 
				CustomJpaRepository<Cidade, Long>, CidadeRepositoryQueries, 
										JpaSpecificationExecutor<Cidade>{

//	SELECT c FROM Cidade c JOIN FETCH c.estado e WHERE e.id = :id
	List<Cidade> queryByEstado_Id(Long id);
	
	@Query("SELECT c FROM Cidade c JOIN FETCH c.estado WHERE c.id = :id")
	Optional<Cidade> consultarPorId(@Param("id") Long id);
}

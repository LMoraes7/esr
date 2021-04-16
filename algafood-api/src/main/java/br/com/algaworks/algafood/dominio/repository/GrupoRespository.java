package br.com.algaworks.algafood.dominio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Grupo;
import br.com.algaworks.algafood.dominio.repository.queries.GrupoRepositoryQueries;

@Repository
public interface GrupoRespository extends JpaRepository<Grupo, Long>, GrupoRepositoryQueries{

	@Query("SELECT g FROM Grupo g JOIN FETCH g.permissoes WHERE g.id = :id")
	Optional<Grupo> consultarPorId(@Param(value = "id") Long id);
}

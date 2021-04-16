package br.com.algaworks.algafood.dominio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Usuario;
import br.com.algaworks.algafood.dominio.repository.queries.UsuarioRepositoryQueries;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueries{

	@Query("SELECT u FROM Usuario u JOIN FETCH u.grupos WHERE u.id = :id")
	Optional<Usuario> consultarPorId(@Param(value = "id") Long id);
}

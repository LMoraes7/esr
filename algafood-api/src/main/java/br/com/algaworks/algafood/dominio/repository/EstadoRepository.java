package br.com.algaworks.algafood.dominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

	
//	SELECT e FROM Estado e WHERE e.nome LIKE %:nome%
	List<Estado> findByNomeContaining(String nome);
}

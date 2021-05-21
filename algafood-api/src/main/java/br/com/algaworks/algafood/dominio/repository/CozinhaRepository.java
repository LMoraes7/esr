package br.com.algaworks.algafood.dominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

	List<Cozinha> findByNomeContaining(String nome);
}

package br.com.algaworks.algafood.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.algafood.dominio.modelo.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}

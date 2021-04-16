package br.com.algaworks.algafood.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}

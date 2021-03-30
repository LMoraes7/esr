package br.com.algaworks.algafood.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}

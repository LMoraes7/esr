package br.com.algaworks.algafood.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}

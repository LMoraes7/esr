package br.com.algaworks.algafood.dominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

//	SELECT c FROM Cidade c LEFT JOIN FETCH c.estado e WHERE e.id = :id
	List<Cidade> queryByEstado_Id(Long id);
}

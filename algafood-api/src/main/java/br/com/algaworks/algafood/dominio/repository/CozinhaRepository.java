package br.com.algaworks.algafood.dominio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

//	SELECT c FROM Cozinha c WHERE c.nome LIKE %:nome%
	List<Cozinha> findByNomeContaining(String nome);
	
//	Esse método possui a mesma função que o mesmo que o de cima 
//		-> (queryBy == findBy)
//	List<Cozinha> queryByNomeContaining(String nome);

//	Esse método possui a mesma função que os de cima 
//		-> (readBy == findBy && readBy == queryBy)
//	List<Cozinha> readByNomeContaining(String nome);
	
//	Esse método possui a mesma função que os de cima 
//		-> (getBy == findBy && getBy == queryBy && getBy == readBy)
//	List<Cozinha> getByNomeContaining(String nome);
	
//	SELECT c FROM Cozinha c WHERE c.nome = :nome
	List<Cozinha> findByNome(String nome);
	
//	SELECT c FROM Cozinha c WHERE c.nome LIKE %:nome% LIMIT 1
	Optional<Cozinha> findFirtsByNomeContaining(String nome);

//	SELECT c FROM Cozinha c WHERE c.nome LIKE %:nome% LIMIT 2
	List<Cozinha> findTop2ByNomeContaining(String nome);
	
	@Query("SELECT c FROM Cozinha c WHERE c.nome = :nome")
	List<Cozinha> consultarPorNome(@Param("nome") String nome);
}

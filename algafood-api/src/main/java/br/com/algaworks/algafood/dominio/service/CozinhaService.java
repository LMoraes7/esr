package br.com.algaworks.algafood.dominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeEmUsoException;
import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
import br.com.algaworks.algafood.dominio.modelo.Cozinha;
import br.com.algaworks.algafood.dominio.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private ConsultarOuFalhar auxiliar;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return this.repository.save(cozinha);
	}
	
	public List<Cozinha> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Cozinha consultarPorId(Long id) {
		return (Cozinha) auxiliar.consultarPorId(this.repository.findById(id));
	}
	
	public Cozinha consultarPorIdParaValidarRequisicao(Long id) {
		return (Cozinha) auxiliar.consultarPorIdParaValidarRequisicao(this.repository.findById(id));
	}
	
	@Transactional
	public void deletarPorId(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("A entidade informada não pode ser excluída!");
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeInexistenteException("Entidade inexistente!");
		}
	}

	public List<Cozinha> consultarPorNome(String nome) {
		return this.repository.comNomeSemelhante(nome);
	}
}
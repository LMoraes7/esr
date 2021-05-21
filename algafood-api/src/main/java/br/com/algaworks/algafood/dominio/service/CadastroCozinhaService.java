package br.com.algaworks.algafood.dominio.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.dominio.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.dominio.modelo.Cozinha;
import br.com.algaworks.algafood.dominio.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private CozinhaRepository cozinhaRepository;

	public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
		this.cozinhaRepository = cozinhaRepository;
	}

	public Cozinha salvar(Cozinha cozinha) {
		return this.cozinhaRepository.save(cozinha);
	}
	
	public void removerPorId(Long id) {
		try {
			this.cozinhaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(e.getMessage());
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
}

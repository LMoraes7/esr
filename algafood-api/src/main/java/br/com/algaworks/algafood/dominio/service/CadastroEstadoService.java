package br.com.algaworks.algafood.dominio.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.dominio.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.dominio.modelo.Estado;
import br.com.algaworks.algafood.dominio.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private EstadoRepository estadoRepository;

	public CadastroEstadoService(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}

	public Estado salvar(Estado estado) {
		return this.estadoRepository.save(estado);
	}
	
	public void removerPorId(Long id) {
		try {
			this.estadoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(e.getMessage());
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
}
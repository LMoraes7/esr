package br.com.algaworks.algafood.dominio.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.dominio.modelo.Cidade;
import br.com.algaworks.algafood.dominio.repository.CidadeRepository;
import br.com.algaworks.algafood.dominio.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	private CidadeRepository cidadeRepository;
	private EstadoRepository estadoRepository;

	public CadastroCidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
		this.cidadeRepository = cidadeRepository;
		this.estadoRepository = estadoRepository;
	}
	
	public Cidade salvar(Cidade cidade) {
		Long idEstado = cidade.getEstado().getId();
		this.estadoRepository.findById(idEstado)
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Não existe de cadastro de Estado com código: "+idEstado));
		return this.cidadeRepository.save(cidade);
	}

	public void removerPorId(Long id) {
		try {
			this.cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
}
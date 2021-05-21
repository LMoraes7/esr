package br.com.algaworks.algafood.dominio.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.CozinhaRepository;
import br.com.algaworks.algafood.dominio.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private RestauranteRepository restauranteRepository;
	private CozinhaRepository cozinhaRepository;

	public CadastroRestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
		this.restauranteRepository = restauranteRepository;
		this.cozinhaRepository = cozinhaRepository;
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		this.cozinhaRepository.findById(idCozinha)
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Não existe de cadastro de Cozinha com código: "+idCozinha));
		return this.restauranteRepository.save(restaurante);
	}
	
	public void removerPorId(Long id) {
		try {
			this.restauranteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(e.getMessage());
		}
	}
}

package br.com.algaworks.algafood.dominio.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private Auxiliar auxiliar;
	
	public Restaurante salvar(Restaurante restaurante) {
		this.cozinhaService.consultarPorId(restaurante.getCozinha().getId());
		return this.repository.save(restaurante);
	}
	
	public List<Restaurante> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Restaurante consultarPorId(Long id) {
		return (Restaurante) this.auxiliar.consultarPorId(this.repository.findById(id));
	}
	
	public void deletarPorId(Long id) {
		this.auxiliar.deletarPorId(this.repository.findById(id));
		this.repository.deleteById(id);
		
	}

	public Restaurante atualizar(Long id, Restaurante restaurante) {
		Restaurante restauranteEntidade = this.consultarPorId(id);
		BeanUtils.copyProperties(restaurante, restauranteEntidade, "id");
		return restauranteEntidade;
	}
}
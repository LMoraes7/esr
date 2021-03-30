package br.com.algaworks.algafood.dominio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private Auxiliar auxiliar;
	
	public void salvar(Restaurante restaurante) {
		this.repository.save(restaurante);
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
}
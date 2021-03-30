package br.com.algaworks.algafood.dominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.modelo.Estado;
import br.com.algaworks.algafood.dominio.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public void salvar(Estado estado) {
		this.repository.save(estado);
	}
	
	public List<Estado> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Optional<Estado> consultarPorId(Long id) {
		return this.repository.findById(id);
	}
	
	public void deletarPorId(Long id) {
		this.repository.deleteById(id);
	}
}

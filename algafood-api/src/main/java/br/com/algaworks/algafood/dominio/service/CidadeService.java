package br.com.algaworks.algafood.dominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.modelo.Cidade;
import br.com.algaworks.algafood.dominio.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	public void salvar(Cidade cidade) {
		this.repository.save(cidade);
	}
	
	public List<Cidade> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Optional<Cidade> consultarPorId(Long id) {
		return this.repository.findById(id);
	}
	
	public void deletarPorId(Long id) {
		this.repository.deleteById(id);
	}
}

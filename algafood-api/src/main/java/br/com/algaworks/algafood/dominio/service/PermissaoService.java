package br.com.algaworks.algafood.dominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.modelo.Permissao;
import br.com.algaworks.algafood.dominio.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository repository;
	
	public void salvar(Permissao permissao) {
		this.repository.save(permissao);
	}
	
	public List<Permissao> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Optional<Permissao> consultarPorId(Long id) {
		return this.repository.findById(id);
	}
	
	public void deletarPorId(Long id) {
		this.repository.deleteById(id);
	}
}

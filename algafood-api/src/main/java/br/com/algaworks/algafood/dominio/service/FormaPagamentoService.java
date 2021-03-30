package br.com.algaworks.algafood.dominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.modelo.FormaPagamento;
import br.com.algaworks.algafood.dominio.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repository;
	
	public void salvar(FormaPagamento formaPagamento) {
		this.repository.save(formaPagamento);
	}
	
	public List<FormaPagamento> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Optional<FormaPagamento> consultarPorId(Long id) {
		return this.repository.findById(id);
	}
	
	public void deletarPorId(Long id) {
		this.repository.deleteById(id);
	}
}

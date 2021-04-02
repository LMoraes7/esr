package br.com.algaworks.algafood.dominio.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeEmUsoException;
import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
import br.com.algaworks.algafood.dominio.modelo.Estado;
import br.com.algaworks.algafood.dominio.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	@Autowired
	private Auxiliar auxiliar;
	
	public Estado salvar(Estado estado) {
		return this.repository.save(estado);
	}
	
	public List<Estado> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Estado consultarPorId(Long id) {
		return (Estado) this.auxiliar.consultarPorId(this.repository.findById(id));
	}
	
	public void deletarPorId(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Entidade não pode ser excluída!");
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeInexistenteException("Entidade inexistente!");
		}
	}

	public Estado atualizar(Long id, Estado estado) {
		Estado estadoEntidade = this.consultarPorId(id);
		BeanUtils.copyProperties(estado, estadoEntidade, "id");
		return estadoEntidade;
	}
}
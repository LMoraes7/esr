package br.com.algaworks.algafood.dominio.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
import br.com.algaworks.algafood.dominio.modelo.Cidade;
import br.com.algaworks.algafood.dominio.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private ConsultarOuFalhar auxiliar;
	
	@Autowired
	private EstadoService estadoService;
	
	public Cidade salvar(Cidade cidade) {
		this.estadoService.consultarPorIdParaValidarRequisicao(cidade.getEstado().getId());
		return this.repository.save(cidade);
	}
	
	public List<Cidade> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Cidade consultarPorId(Long id) {
		return (Cidade) this.auxiliar.consultarPorId(this.repository.consultarPorId(id));
	}
	
	public void deletarPorId(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeInexistenteException("Entidade inexistente!");
		}
	}

	public Cidade atualizar(Long id, Cidade cidade) {
		this.estadoService.consultarPorIdParaValidarRequisicao(cidade.getEstado().getId());
		Cidade cidadeEntidade = this.consultarPorId(id);
		BeanUtils.copyProperties(cidade, cidadeEntidade, "id");
		return cidadeEntidade;
	}

	public List<Cidade> consultarPorNome(String nome) {
		return this.repository.comNomeSemelhante(nome);
	}
}

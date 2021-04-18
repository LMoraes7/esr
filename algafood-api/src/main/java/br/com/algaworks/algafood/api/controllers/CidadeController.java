package br.com.algaworks.algafood.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algaworks.algafood.dominio.modelo.Cidade;
import br.com.algaworks.algafood.dominio.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> consultarTodos() {
		return this.cidadeService.consultarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> consultarPorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.cidadeService.consultarPorId(id));
	}
	
	@GetMapping("/buscar-nome")
	public List<Cidade> consultarPorNome(String nome) {
		return this.cidadeService.consultarPorNome(nome);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarCidade(@RequestBody @Valid Cidade cidade, UriComponentsBuilder uriBuilder) {
		cidade = this.cidadeService.salvar(cidade);
		URI uri = uriBuilder.path("http://localhost:8080/cidades/{id}").buildAndExpand(cidade.getId()).toUri();
		return ResponseEntity.created(uri).body(cidade);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarCidade(@PathVariable("id") Long id, @RequestBody @Valid Cidade cidade) {
		cidade = this.cidadeService.atualizar(id, cidade);
		return ResponseEntity.ok(cidade);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCidade(@PathVariable("id") Long id) {
		this.cidadeService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
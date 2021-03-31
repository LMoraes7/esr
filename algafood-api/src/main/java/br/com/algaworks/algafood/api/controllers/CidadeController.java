package br.com.algaworks.algafood.api.controllers;

import java.net.URI;
import java.util.List;

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

import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
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
		try {
			return ResponseEntity.ok(this.cidadeService.consultarPorId(id));
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarCidade(@RequestBody Cidade cidade, UriComponentsBuilder uriBuilder) {
		try {
			cidade = this.cidadeService.salvar(cidade);
			URI uri = uriBuilder.path("http://localhost:8080/cidades/{id}").buildAndExpand(cidade.getId()).toUri();
			return ResponseEntity.created(uri).body(cidade);
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarCidade(@PathVariable("id") Long id, @RequestBody Cidade cidade) {
		try {
			cidade = this.cidadeService.atualizar(id, cidade);
			return ResponseEntity.ok(cidade);
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCidade(@PathVariable("id") Long id) {
		try {
			this.cidadeService.deletarPorId(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
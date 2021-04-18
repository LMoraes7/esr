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

import br.com.algaworks.algafood.dominio.modelo.Estado;
import br.com.algaworks.algafood.dominio.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listarEstados() {
		return this.estadoService.consultarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> consultarPorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.estadoService.consultarPorId(id));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Estado> cadastrarEstado(@RequestBody @Valid Estado estado, UriComponentsBuilder uriBuilder) {
		estado = this.estadoService.salvar(estado);
		URI uri = uriBuilder.path("http://localhost:8080/estados/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(estado);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizarEstado(@PathVariable("id") Long id, @RequestBody @Valid Estado estado) {
		estado = this.estadoService.atualizar(id, estado);
		return ResponseEntity.ok(estado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarEstado(@PathVariable("id") Long id) {
		this.estadoService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
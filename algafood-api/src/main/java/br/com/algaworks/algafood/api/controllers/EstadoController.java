package br.com.algaworks.algafood.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.algaworks.algafood.dominio.exceptions.EntidadeEmUsoException;
import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
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
		try {
			return ResponseEntity.ok(this.estadoService.consultarPorId(id));
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Estado> cadastrarEstado(@RequestBody Estado estado, UriComponentsBuilder uriBuilder) {
		estado = this.estadoService.salvar(estado);
		URI uri = uriBuilder.path("http://localhost:8080/estados/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(estado);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizarEstado(@PathVariable("id") Long id, @RequestBody Estado estado) {
		try {
			estado = this.estadoService.atualizar(id, estado);
			return ResponseEntity.ok(estado);
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarEstado(@PathVariable("id") Long id) {
		try {
			this.estadoService.deletarPorId(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
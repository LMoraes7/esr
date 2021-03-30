package br.com.algaworks.algafood.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
import br.com.algaworks.algafood.dominio.modelo.Cozinha;
import br.com.algaworks.algafood.dominio.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public List<Cozinha> consultarTodasCozinhas() {
		return this.cozinhaService.consultarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> consultarCozinhaPorId(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(this.cozinhaService.consultarPorId(id));
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Cozinha> cadastarCozinha(@RequestBody Cozinha cozinha, UriComponentsBuilder uriBuilder) {
		cozinha = this.cozinhaService.salvar(cozinha);
		URI uri = uriBuilder.path("http://localhost:8080/cozinhas/{id}").buildAndExpand(cozinha.getId()).toUri();
		return ResponseEntity.created(uri).body(cozinha);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Cozinha> atualizarCozinha(@PathVariable("id") Long id, @RequestBody Cozinha cozinha) {
		try {
			Cozinha cozinhaEntidade = this.cozinhaService.consultarPorId(id);
			BeanUtils.copyProperties(cozinha, cozinhaEntidade, "id");
			return ResponseEntity.ok(cozinhaEntidade);
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCozinha(@PathVariable("id") Long id) {
		try {
			this.cozinhaService.deletarPorId(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} 
	}
}
package br.com.algaworks.algafood.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping
	public List<Restaurante> consultarTodosRestaurantes() {
		return this.restauranteService.consultarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> consultarRestaurantePorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.restauranteService.consultarPorId(id));
	}
	
	@GetMapping("/buscar-nome")
	public List<Restaurante> consultarRestaurantePorNome(String nome) {
		return this.restauranteService.consultarPorNome(nome);
	}
	
	@GetMapping("/buscar-frete-gratis")
	public List<Restaurante> consultarRestauranteFreteGratis() {
		return this.restauranteService.consultarComFreteGratis();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarRestaurante(@RequestBody @Valid Restaurante restaurante, UriComponentsBuilder uriBuilder) {
		restaurante = this.restauranteService.salvar(restaurante);
		URI uri = uriBuilder.path("http://localhost:8080/restaurantes/{id}").buildAndExpand(restaurante.getId()).toUri();
		return ResponseEntity.created(uri).body(restaurante);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarRestaurante(@PathVariable("id") Long id, @RequestBody @Valid Restaurante restaurante) {
		restaurante = this.restauranteService.atualizar(id, restaurante);
		return ResponseEntity.ok(restaurante);
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarParcialRestaurante(@PathVariable("id") Long id, @RequestBody Map<String , Object> campos) {
		Restaurante restaurante = this.restauranteService.atualizarParcial(id, campos);
		return ResponseEntity.ok().body(restaurante);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarRestaurante(@PathVariable("id") Long id) {
		this.restauranteService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
}
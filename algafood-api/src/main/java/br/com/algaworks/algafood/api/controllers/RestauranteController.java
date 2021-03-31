package br.com.algaworks.algafood.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.service.CozinhaService;
import br.com.algaworks.algafood.dominio.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public List<Restaurante> consultarTodosRestaurantes() {
		return this.restauranteService.consultarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> consultarRestaurantePorId(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(this.restauranteService.consultarPorId(id));
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrarRestaurante(@RequestBody Restaurante restaurante, UriComponentsBuilder uriBuilder) {
		try {
			restaurante = this.restauranteService.salvar(restaurante, this.cozinhaService);
			URI uri = uriBuilder.path("http://localhost:8080/restaurantes/{id}").buildAndExpand(restaurante.getId()).toUri();
			return ResponseEntity.created(uri).body(restaurante);
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public void atualizarRestaurante(@PathVariable("id") Long id, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteEntidade = this.restauranteService.consultarPorId(id);
			BeanUtils.copyProperties(restaurante, restauranteEntidade, "id");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
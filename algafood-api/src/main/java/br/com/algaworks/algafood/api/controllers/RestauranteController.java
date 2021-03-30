package br.com.algaworks.algafood.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
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
		try {
			return ResponseEntity.ok(this.restauranteService.consultarPorId(id));
		} catch (EntidadeInexistenteException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
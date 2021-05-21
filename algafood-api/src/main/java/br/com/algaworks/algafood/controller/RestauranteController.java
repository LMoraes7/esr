package br.com.algaworks.algafood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

import br.com.algaworks.algafood.dominio.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.RestauranteRepository;
import br.com.algaworks.algafood.dominio.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private RestauranteRepository restauranteRepository;
	private CadastroRestauranteService cadastroRestaurante;

	public RestauranteController(RestauranteRepository restauranteRepository, CadastroRestauranteService cadastroRestaurante) {
		this.restauranteRepository = restauranteRepository;
		this.cadastroRestaurante = cadastroRestaurante;
	}

	@GetMapping
	public ResponseEntity<List<Restaurante>> listarTodos() {
		return ResponseEntity.ok(this.restauranteRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> listarPorId(@PathVariable("id") Long id) {
		Optional<Restaurante> restauranteOptional = this.restauranteRepository.findById(id);
		if(restauranteOptional.isPresent())
			return ResponseEntity.ok(restauranteOptional.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = this.cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Restaurante restaurante) {
		Optional<Restaurante> restauranteOptional = this.restauranteRepository.findById(id);
		if(restauranteOptional.isPresent()) {
			Restaurante restauranteEntidade = restauranteOptional.get();
			BeanUtils.copyProperties(restaurante, restauranteEntidade, "id", "formasPagamentos", "endereco", "dataCadastro", "produtos");
			try {
				restauranteEntidade = this.cadastroRestaurante.salvar(restauranteEntidade);
				return ResponseEntity.ok(restauranteEntidade);
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id) {
		try {
			this.cadastroRestaurante.removerPorId(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
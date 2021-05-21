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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;
import br.com.algaworks.algafood.dominio.repository.CozinhaRepository;
import br.com.algaworks.algafood.dominio.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	private CozinhaRepository cozinhaRepository;
	private CadastroCozinhaService cadastroCozinha;

	public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinha) {
		this.cozinhaRepository = cozinhaRepository;
		this.cadastroCozinha = cadastroCozinha;
	}

	@GetMapping
	public ResponseEntity<List<Cozinha>> listarTodos(@RequestParam(name = "nome", required = false) String nome) {
		if(nome == null)
			return ResponseEntity.ok(this.cozinhaRepository.findAll());
		return ResponseEntity.ok(this.cozinhaRepository.findByNomeContaining(nome));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> listarPorId(@PathVariable("id") Long id) {
		Optional<Cozinha> cozinhaOptional = this.cozinhaRepository.findById(id);
		if(cozinhaOptional.isPresent()) 
			return ResponseEntity.ok(cozinhaOptional.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody Cozinha cozinha) {
		cozinha = this.cadastroCozinha.salvar(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaOptional = this.cozinhaRepository.findById(id);
		if(cozinhaOptional.isPresent()) {
			Cozinha cozinhaEntity = cozinhaOptional.get();
			BeanUtils.copyProperties(cozinha, cozinhaEntity, "id");
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id) {
		this.cadastroCozinha.removerPorId(id);
		return ResponseEntity.noContent().build();
	}
}
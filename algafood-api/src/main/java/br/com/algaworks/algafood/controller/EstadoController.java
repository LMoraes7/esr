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

import br.com.algaworks.algafood.dominio.exception.EntidadeEmUsoException;
import br.com.algaworks.algafood.dominio.exception.EntidadeNaoEncontradaException;
import br.com.algaworks.algafood.dominio.modelo.Estado;
import br.com.algaworks.algafood.dominio.repository.EstadoRepository;
import br.com.algaworks.algafood.dominio.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	private EstadoRepository estadoRepository;
	private CadastroEstadoService cadastroEstado;

	public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService cadastroEstado) {
		this.estadoRepository = estadoRepository;
		this.cadastroEstado = cadastroEstado;
	}

	@GetMapping
	public ResponseEntity<List<Estado>> listarTodos() {
		return ResponseEntity.ok(this.estadoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> listarPorId(@PathVariable("id") Long id) {
		Optional<Estado> estadoOptional = this.estadoRepository.findById(id);
		if(estadoOptional.isPresent()) 
			return ResponseEntity.ok(estadoOptional.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody Estado estado) {
		estado = this.cadastroEstado.salvar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Estado estado) {
		Optional<Estado> estadoOptional = this.estadoRepository.findById(id);
		if(estadoOptional.isPresent()) {
			Estado estadoEntity = estadoOptional.get();
			BeanUtils.copyProperties(estado, estadoEntity, "id");
			return ResponseEntity.ok(estado);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id) {
		try {
			this.cadastroEstado.removerPorId(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
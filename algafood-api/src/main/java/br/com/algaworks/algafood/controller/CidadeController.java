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
import br.com.algaworks.algafood.dominio.modelo.Cidade;
import br.com.algaworks.algafood.dominio.repository.CidadeRepository;
import br.com.algaworks.algafood.dominio.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	private CidadeRepository cidadeRepository;
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listarTodos() {
		return ResponseEntity.ok(this.cidadeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> listarPorId(@PathVariable("id") Long id) {
		Optional<Cidade> cidadeOptional = this.cidadeRepository.findById(id);
		if(cidadeOptional.isPresent()) 
			return ResponseEntity.ok(cidadeOptional.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody Cidade cidade) {
		try {
			cidade = this.cadastroCidade.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Cidade cidade) {
		Optional<Cidade> cidadeOptional = this.cidadeRepository.findById(id);
		if(cidadeOptional.isPresent()) {
			Cidade cidadeEntity = cidadeOptional.get();
			BeanUtils.copyProperties(cidade, cidadeEntity, "id");
			try {
				cidadeEntity = this.cadastroCidade.salvar(cidadeEntity);
				return ResponseEntity.ok(cidadeEntity);
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id) {
		try {
			this.cadastroCidade.removerPorId(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
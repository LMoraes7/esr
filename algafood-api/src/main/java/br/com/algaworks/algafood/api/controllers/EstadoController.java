package br.com.algaworks.algafood.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.algafood.dominio.modelo.Estado;
import br.com.algaworks.algafood.dominio.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoDataService;
	
	@GetMapping
	public List<Estado> listarEstados() {
		return this.estadoDataService.consultarTodos();
	}
}

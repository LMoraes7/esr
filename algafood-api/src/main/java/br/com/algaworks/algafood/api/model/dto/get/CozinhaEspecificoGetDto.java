package br.com.algaworks.algafood.api.model.dto.get;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;

public class CozinhaEspecificoGetDto {

	private Long id;
	private String nome;
	private List<RestauranteGetDto> restaurantes = new ArrayList<RestauranteGetDto>();

	public CozinhaEspecificoGetDto(Cozinha cozinha) {
		this.id = cozinha.getId();
		this.nome = cozinha.getNome();
		this.restaurantes = cozinha.getRestaurantes().stream().map(RestauranteGetDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<RestauranteGetDto> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<RestauranteGetDto> restaurantes) {
		this.restaurantes = restaurantes;
	}
}
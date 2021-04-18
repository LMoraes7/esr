package br.com.algaworks.algafood.api.model.dto.get;

import br.com.algaworks.algafood.dominio.modelo.Estado;

public class EstadoGetDto {

	private Long id;
	private String nome;

	public EstadoGetDto(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
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
}
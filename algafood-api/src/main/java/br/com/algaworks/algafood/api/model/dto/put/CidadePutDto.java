package br.com.algaworks.algafood.api.model.dto.put;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.algaworks.algafood.api.model.dto.EstadoIdDto;

public class CidadePutDto {

	@NotNull
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	private EstadoIdDto estado;

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

	public EstadoIdDto getEstado() {
		return estado;
	}

	public void setEstado(EstadoIdDto estado) {
		this.estado = estado;
	}
}
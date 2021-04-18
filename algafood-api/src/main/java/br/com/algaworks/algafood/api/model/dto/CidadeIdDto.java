package br.com.algaworks.algafood.api.model.dto;

import javax.validation.constraints.NotNull;

public class CidadeIdDto {

	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
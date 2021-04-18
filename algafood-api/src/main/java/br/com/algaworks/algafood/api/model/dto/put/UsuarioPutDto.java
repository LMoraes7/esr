package br.com.algaworks.algafood.api.model.dto.put;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioPutDto {

	@NotNull
	private Long id;
	@NotBlank
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
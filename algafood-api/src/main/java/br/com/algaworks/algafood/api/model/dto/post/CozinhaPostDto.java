package br.com.algaworks.algafood.api.model.dto.post;

import javax.validation.constraints.NotBlank;

public class CozinhaPostDto {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
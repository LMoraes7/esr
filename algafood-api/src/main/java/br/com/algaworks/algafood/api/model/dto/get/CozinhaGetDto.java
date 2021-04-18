package br.com.algaworks.algafood.api.model.dto.get;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;

public class CozinhaGetDto {

	private Long id;
	private String nome;

	public CozinhaGetDto(Cozinha cozinha) {
		this.id = cozinha.getId();
		this.nome = cozinha.getNome();
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
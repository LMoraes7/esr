package br.com.algaworks.algafood.api.model.dto.post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.algaworks.algafood.api.model.dto.EstadoIdDto;

public class CidadePostDto {

	@NotBlank
	private String nome;
	@NotNull
	private EstadoIdDto estado;

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
package br.com.algaworks.algafood.api.model.dto.get;

import br.com.algaworks.algafood.dominio.modelo.Cidade;

public class CidadeGetDto {

	private Long id;
	private String nome;
	private EstadoGetDto estado;

	public CidadeGetDto(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.estado = new EstadoGetDto(cidade.getEstado());
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

	public EstadoGetDto getEstado() {
		return estado;
	}

	public void setEstado(EstadoGetDto estado) {
		this.estado = estado;
	}
}
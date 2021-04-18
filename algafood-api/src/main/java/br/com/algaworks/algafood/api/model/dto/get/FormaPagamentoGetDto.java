package br.com.algaworks.algafood.api.model.dto.get;

import br.com.algaworks.algafood.dominio.modelo.FormaPagamento;

public class FormaPagamentoGetDto {

	private Long id;
	private String descricao;

	public FormaPagamentoGetDto(FormaPagamento formaPagamento) {
		this.id = formaPagamento.getId();
		this.descricao = formaPagamento.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
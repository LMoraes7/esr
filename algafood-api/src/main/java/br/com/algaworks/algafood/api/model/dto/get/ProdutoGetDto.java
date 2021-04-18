package br.com.algaworks.algafood.api.model.dto.get;

import java.math.BigDecimal;

import br.com.algaworks.algafood.dominio.modelo.Produto;

public class ProdutoGetDto {

	private Long id;
	private String nome;
	private BigDecimal preco;
	private Boolean ativo;

	public ProdutoGetDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.ativo = produto.getAtivo();
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
package br.com.algaworks.algafood.api.model.dto.put;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.algaworks.algafood.api.model.dto.RestauranteIdDto;

public class ProdutoPutDto {

	@NotNull
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@PositiveOrZero
	private BigDecimal preco;
	@NotNull
	private Boolean ativo;
	@NotNull
	private RestauranteIdDto restaurante;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public RestauranteIdDto getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(RestauranteIdDto restaurante) {
		this.restaurante = restaurante;
	}
}
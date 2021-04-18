package br.com.algaworks.algafood.api.model.dto.post;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.algaworks.algafood.api.model.dto.RestauranteIdDto;

public class ProdutoPostDto {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@PositiveOrZero
	private BigDecimal preco;
	@NotNull
	private RestauranteIdDto restaurante;

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

	public RestauranteIdDto getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(RestauranteIdDto restaurante) {
		this.restaurante = restaurante;
	}
}
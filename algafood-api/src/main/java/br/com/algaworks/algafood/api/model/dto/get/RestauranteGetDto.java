package br.com.algaworks.algafood.api.model.dto.get;

import java.math.BigDecimal;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;

public class RestauranteGetDto {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;

	public RestauranteGetDto(Restaurante restaurante) {
		this.id = restaurante.getId();
		this.nome = restaurante.getNome();
		this.taxaFrete = restaurante.getTaxaFrete();
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

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}
}
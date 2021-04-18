package br.com.algaworks.algafood.api.model.dto.post;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.algaworks.algafood.api.model.dto.CozinhaIdDto;
import br.com.algaworks.algafood.api.model.dto.FormaPagamentoIdDto;

public class RestaurantePostDto {

	@NotBlank
	private String nome;
	@PositiveOrZero
	private BigDecimal taxaFrete;
	@NotNull
	private CozinhaIdDto cozinha;
	@NotNull
	private EnderecoPostDto endereco;
	@NotNull
	private List<FormaPagamentoIdDto> formasPagamento = new ArrayList<FormaPagamentoIdDto>();

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

	public CozinhaIdDto getCozinha() {
		return cozinha;
	}

	public void setCozinha(CozinhaIdDto cozinha) {
		this.cozinha = cozinha;
	}

	public EnderecoPostDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoPostDto endereco) {
		this.endereco = endereco;
	}

	public List<FormaPagamentoIdDto> getFormasPagamento() {
		return Collections.unmodifiableList(formasPagamento);
	}

	public void setFormasPagamento(List<FormaPagamentoIdDto> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}
}
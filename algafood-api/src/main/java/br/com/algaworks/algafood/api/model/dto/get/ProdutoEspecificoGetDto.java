package br.com.algaworks.algafood.api.model.dto.get;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.algaworks.algafood.dominio.modelo.Produto;

public class ProdutoEspecificoGetDto {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;
	private List<FormaPagamentoGetDto> formasPagamento = new ArrayList<FormaPagamentoGetDto>();

	public ProdutoEspecificoGetDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getNome();
		this.preco = produto.getPreco();
		this.ativo = produto.getAtivo();
		this.formasPagamento = produto.getRestaurante().getFormasPagamento().stream().map(FormaPagamentoGetDto::new)
				.collect(Collectors.toList());
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

	public List<FormaPagamentoGetDto> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<FormaPagamentoGetDto> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}
}
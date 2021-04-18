package br.com.algaworks.algafood.api.model.dto.get;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;

public class RestauranteEspecificoGetDto {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaGetDto cozinha;
	private EnderecoGetDto endereco;
	private List<ProdutoGetDto> produtos = new ArrayList<ProdutoGetDto>();

	public RestauranteEspecificoGetDto(Restaurante restaurante) {
		this.id = restaurante.getId();
		this.nome = restaurante.getNome();
		this.taxaFrete = restaurante.getTaxaFrete();
		this.cozinha = new CozinhaGetDto(restaurante.getCozinha());
		this.endereco = new EnderecoGetDto(restaurante.getEndereco());
		this.produtos = restaurante.getProdutos().stream().map(ProdutoGetDto::new).collect(Collectors.toList());
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

	public CozinhaGetDto getCozinha() {
		return cozinha;
	}

	public void setCozinha(CozinhaGetDto cozinha) {
		this.cozinha = cozinha;
	}

	public EnderecoGetDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoGetDto endereco) {
		this.endereco = endereco;
	}

	public List<ProdutoGetDto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoGetDto> produtos) {
		this.produtos = produtos;
	}
}
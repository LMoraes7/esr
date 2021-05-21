package br.com.algaworks.algafood.dominio.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cozinha cozinha;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	private List<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<Produto>(); 

	@Deprecated
	public Restaurante() {
	}

	public Restaurante(String nome, BigDecimal taxaFrete, Cozinha cozinha, Endereco endereco) {
		this.nome = nome;
		this.taxaFrete = taxaFrete;
		this.cozinha = cozinha;
		this.endereco = endereco;
	}
	
	public Cozinha getCozinha() {
		return cozinha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurante other = (Restaurante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
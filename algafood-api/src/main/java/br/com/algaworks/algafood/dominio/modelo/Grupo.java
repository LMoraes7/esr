package br.com.algaworks.algafood.dominio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "grupos_permissoes", 
		joinColumns = @JoinColumn(name = "grupo_id"), 
		inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private List<Permissao> permissoes = new ArrayList<Permissao>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}
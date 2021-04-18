package br.com.algaworks.algafood.api.model.dto.post;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.algaworks.algafood.api.model.dto.GrupoIdDto;

public class UsuarioPostDto {

	@NotBlank
	private String nome;
	@Email
	private String email;
	@NotBlank
	private String senha;
	@NotNull
	private List<GrupoIdDto> grupos = new ArrayList<GrupoIdDto>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<GrupoIdDto> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoIdDto> grupos) {
		this.grupos = grupos;
	}
}
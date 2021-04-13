package br.com.algaworks.algafood.infraestrutura.repository.spec;

import org.springframework.data.jpa.domain.Specification;

import br.com.algaworks.algafood.dominio.modelo.Cozinha;

public class CozinhaSpecs {

	public static Specification<Cozinha> comNomeSemelhante(String nome) {
		return (root, query, builder) ->
			builder.like(root.get("nome"), "%"+nome+"%");
	}
}

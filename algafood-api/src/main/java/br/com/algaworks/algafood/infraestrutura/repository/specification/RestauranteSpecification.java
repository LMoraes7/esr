package br.com.algaworks.algafood.infraestrutura.repository.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;

public class RestauranteSpecification {

	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
}

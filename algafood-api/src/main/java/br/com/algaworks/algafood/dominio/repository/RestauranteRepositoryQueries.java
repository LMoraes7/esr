package br.com.algaworks.algafood.dominio.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.algaworks.algafood.dominio.modelo.Restaurante;

public interface RestauranteRepositoryQueries {

//	Esse método está implementado na classe RestauranteRepositoryImpl
//	O Spring possui a inteligência de ligar essa classe Impl ao repository Restaurante
//	Porém para esse ligação funcionar é necessário colocar o nome da classe 
//		igual ao nome do repository junto com o sufixo Impl
	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
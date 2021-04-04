package br.com.algaworks.algafood.dominio.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.algaworks.algafood.dominio.exceptions.EntidadeInexistenteException;
import br.com.algaworks.algafood.dominio.modelo.Restaurante;
import br.com.algaworks.algafood.dominio.repository.RestauranteRepository;
import br.com.algaworks.algafood.infraestrutura.repository.spec.RestauranteSpecs;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private Auxiliar auxiliar;
	
	public Restaurante salvar(Restaurante restaurante) {
		this.cozinhaService.consultarPorId(restaurante.getCozinha().getId());
		return this.repository.save(restaurante);
	}
	
	public List<Restaurante> consultarTodos() {
		return this.repository.findAll();
	}
	
	public Restaurante consultarPorId(Long id) {
		return (Restaurante) this.auxiliar.consultarPorId(this.repository.findById(id));
	}
	
	public void deletarPorId(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeInexistenteException("Entidade inexistente!");
		}
	}

	public Restaurante atualizar(Long id, Restaurante restaurante) {
		Restaurante restauranteEntidade = this.consultarPorId(id);
		BeanUtils.copyProperties(restaurante, restauranteEntidade, "id");
		return restauranteEntidade;
	}

	public Restaurante atualizarParcial(Long id, Map<String, Object> campos) {
		Restaurante restauranteEntidade = this.consultarPorId(id);
		
//		A classe ObjectMapper é a classe responsável por converter/serializar Objetos Java em JSON e vice-versa.
		ObjectMapper objectMapper = new ObjectMapper();
		
//		Método para serializar os campos com seus valores informados no JSON para um Objeto do tipo informado.
//		Converta o JSON informado na variável campos para um Objeto Java do tipo Restaurante.
		Restaurante restauranteConvertidoJSON = objectMapper.convertValue(campos, Restaurante.class);
		
		campos.forEach((nomePropriedade, valorPropriedade) -> {
			
//			Busca e retorna o campo de uma classe cujo o parâmetro informado seja igual ao nome do atributo da classe informada.
//			Busque o campo de uma classe Restaurante cujo o nome do seu atributo seja igual ao valor do nomePropriedade.
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			
//			Torna o atributo acessível. Um atributo private não pode ser acessado fora da classe, 
//				porém com a alteração da visibilidade, ele se torna acessível fora da classe.
			field.setAccessible(true);
			
//			Busca e retorna o valor contido em um atributo de um objeto referenciado, 
//				o retorno será o objeto contido no atrubuto
//			Busque o valor contido no atributo referenciado como field dentro do objeto restauranteConvertido
			Object novoValor = ReflectionUtils.getField(field, restauranteConvertidoJSON);
			
//			Altera o valor do campo informado da entidade desejada com o o novo valor.
//			Altere o campo referenciado como field do objeto restauranteEntidade com o valor contido na valorPropriedade.
			ReflectionUtils.setField(field, restauranteEntidade, novoValor);
		});
		return restauranteEntidade;
	}

	public List<Restaurante> consultarPorNome(String nome) {
		return this.repository.findAll(RestauranteSpecs.comNomeSemelhante(nome));
	}
}
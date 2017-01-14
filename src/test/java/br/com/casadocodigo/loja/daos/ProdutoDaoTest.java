package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)// Anotation do Junit Esse é quem vai rodar os testes dentro do Spring.
@ContextConfiguration(classes={JPAConfiguration.class, ProdutoDao.class,DataSourceConfigurationTest.class}) // Essa anotation diz para o Spring onde estão os arquivos de configuração.
@ActiveProfiles("test") // Essa anotação informa qual o profile ativo.
public class ProdutoDaoTest {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Test // Anotation do Junit.org
	@Transactional
	public void deveSomarTodosPrecosPorTipoLivro(){
		
//		ProdutoDao produtoDao = new ProdutoDao(); // Essa classe tem uma dependência do EntityManager. Então precisamos que seja injetada pelo Spring.

//		Cria uma lista de livros para compração do tipo impresso
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO,BigDecimal.TEN).more(3).buildAll();
		
//		Cria uma lista de livros do tipo Ebook
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK,BigDecimal.TEN).more(3).buildAll();
		
//		Grava no banco de dados os livros criados.	
//		Atenção para gravar no banco de dados precisamos de uma trasação assim temo que anotar a class com Transaction
		livrosImpressos.stream().forEach(produtoDao::gravar);
		livrosEbook.stream().forEach(produtoDao::gravar); 
		
//		Busca no banco de dados todos os livros do tipo ebook e soma seu valor.
		BigDecimal valor = produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
		
//		Compara o retorno do banco de dados com o que gravamos.
		Assert.assertEquals(new  BigDecimal(40).setScale(2), valor);
		
		
	} 
	
	
}

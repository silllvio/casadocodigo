package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.daos.ProdutoDao;

@RunWith(SpringJUnit4ClassRunner.class) // Anotation do Junit Esse é quem vai
										// rodar os testes dentro do Spring.
@WebAppConfiguration
@ContextConfiguration(classes = { JPAConfiguration.class, ProdutoDao.class, DataSourceConfigurationTest.class,
		AppWebConfiguration.class, SecurityConfiguration.class }) // Essa anotation diz para o Spring onde
										// estão os arquivos de configuração.
@ActiveProfiles("test") // Essa anotação informa qual o profile ativo.
public class ProdutosControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	private Filter SpringSecurityFilterChain;  

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(SpringSecurityFilterChain).build();
	}

	@Test
	public void deveRetornarParaHomeComOsLivros() throws Exception {

		// Mock que simula uma requisição.
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));

	}

	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form").with(SecurityMockMvcRequestPostProcessors
				.user("user@casadocodigo.com.br").password("123456").roles("USER"))).andExpect(MockMvcResultMatchers
						.status().is(403));
	}
}
















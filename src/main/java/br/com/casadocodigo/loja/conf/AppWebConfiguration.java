package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDao;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDao.class })
public class AppWebConfiguration {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}

/**
 * Classe de configuração da aplicação.
 * 
 * 
 * Na classe AppWebConfiguration nós precisamos usar o recurso de Web MVC do
 * SpringMVC. Podemos fazer isso através de mais uma anotação. Antes da
 * declaração da classe devemos adicionar a anotação: @EnableWebMvc.
 * 
 * 
 * Precisamos também configurar o caminho (pacote) onde o SpringMVC irá
 * encontrar os nossos controllers! Mais uma anotação para esta configuração é
 * necessária: @ComponentScan
 * 
 * 
 * Nosso próximo passo é configurar o projeto para que o SpringMVC consiga
 * encontrar as views. Essa configuração é feita na classe de configuração
 * AppWebConfiguration
 * 
 * 
 * 
 * Este método na classe AppWebConfiguration retorna um objeto do tipo
 * InternalResourceViewResolver (Resolvedor Interno de Recursos de View) que
 * ajuda o SpringMVC a encontrar as views. O setPrefix define o caminho onde
 * estão nossas views, já o setSuffix adiciona a extenção dos arquivos de view.
 * 
 * Note que no final do caminho das views há uma barra "/", ela poderia não
 * estar ai, mas caso não estivesse, teriamos que lembrar que sempre que
 * quisessemos retornar uma view, teriamos que no controller, no retorno da view
 * escrever algo como: return "/pagina.jsp", então colocamos esta barra já na
 * configuração para não termos que nos preocupar com isto.
 * 
 * 
 * 
 * 
 */
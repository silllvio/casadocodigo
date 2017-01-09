package br.com.casadocodigo.loja.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDao.class, FileSaver.class, CarrinhoCompras.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	// Método que mapeia nossas views.

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;
	}
	// Método padrão de retorno de erros

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}

	// Método para converter as datas

	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);

		return conversionService;
	}

	// Método para resolver URL de importação de arquivos.

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	// Método essencial para roda JS

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// Método de cache do Google.

	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5,
				TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);
		return manager;
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
 * MultiPartResolver
 * 
 * Observação: MultipartResolver se refere a um resolvedor de dados multimidia.
 * Quando temos texto e arquivos por exemplo. Os arquivos podem ser: imagem, PDF
 * e outros. Este objeto é que identifica cada um dos recursos enviados e nos
 * fornece uma forma mais simples de manipulalos.
 * 
 * Mesmo tendo feito a configuração do multipartResolver, o Spring ainda não
 * consegue fazer a conversão dos dados. Teremos que configurar mais algumas
 * coisas. Na Servlet.
 * 
 * 
 * // Método de teste de gerenciamento do Cache.
 * 
 * @Bean public CacheManager cachemananger(){ return new
 *       ConcurrentMapCacheManager(); }
 */
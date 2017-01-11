package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getRootConfigClasses() {
	    return new Class[]{SecurityConfiguration.class,AppWebConfiguration.class, JPAConfiguration.class};
	}

//	Método que passa as configuração da aplicação para a Servelt.
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
	    return new Class[] {};
	}

//	Método que passa os request do TomCat para o Spring
	
	@Override
	protected String[] getServletMappings() {
		 return new String[] {"/"};
	}

	
//	Filtro da requisição para o padrão utf8
	
	  @Override
	    protected Filter[] getServletFilters() {
	        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
	        encodingFilter.setEncoding("UTF-8");
	        return new Filter[] {encodingFilter};
	    }
	  
//	  Método para registrar os arquivos MultiPart
	  
	    @Override
	    protected void customizeRegistration(Dynamic registration) {
	        registration.setMultipartConfig(new MultipartConfigElement(""));
	    }
	  
}


/**

Configuração via Servlet para que o TomCat passe requisições para o SpringMVC.

OBS: Existe uma outra maneira através de filtros.






*/
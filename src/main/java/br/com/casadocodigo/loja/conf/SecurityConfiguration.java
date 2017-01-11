package br.com.casadocodigo.loja.conf;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	// 		A order importa na liberação de acesso.
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests()
		.antMatchers("/produtos/form").hasAnyRole("ADMIN")
		.antMatchers("/carrinho").permitAll()
		.antMatchers( HttpMethod.POST, "/produtos").hasAnyRole("ADMIN")
		.antMatchers( HttpMethod.GET, "/produtos").permitAll()
		.antMatchers("/produtos/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/resources/css/**", "/resources/js/**", "/resources/images/**").permitAll()
		.antMatchers("/resources/bootstrap/css/**", "/resources/bootstrap/js/**", "bootstrap/images/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		
		;
	
	}
	
	

}
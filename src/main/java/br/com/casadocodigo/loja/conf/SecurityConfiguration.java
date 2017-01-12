package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.daos.UsuarioDao;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDao usuarioDao;

	// A order importa na liberação de acesso.

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/produtos/form").hasAnyRole("ADMIN").antMatchers("/carrinho/**").permitAll()
				.antMatchers(HttpMethod.POST, "/produtos").hasAnyRole("ADMIN").antMatchers(HttpMethod.GET, "/produtos")
				.permitAll().antMatchers("/produtos/**").permitAll().antMatchers("/").permitAll()
				.antMatchers("/resources/css/**", "/resources/js/**", "/resources/images/**").permitAll()
				.antMatchers("/resources/bootstrap/css/**", "/resources/bootstrap/js/**", "bootstrap/images/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout") )		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao)
		.passwordEncoder(new BCryptPasswordEncoder()); // Encryptação padrão do Spring.
		
		
	}	
	
	
	
	
}
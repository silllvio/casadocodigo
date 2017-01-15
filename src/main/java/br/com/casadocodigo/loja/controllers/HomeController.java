package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.daos.UsuarioDao;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@Cacheable(value="produtosHome")
public class HomeController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("home");
		List<Produto> produtos = produtoDao.listar();
		modelAndView.addObject("produtos", produtos);
		System.out.println("Entrando na Home");
		return modelAndView;
	}
	@ResponseBody
	@Transactional
	@RequestMapping("/url-magica-maluca-sdfasdfsda4f65asd4f56sad46f54asd65456d98465a46584")
	public String urlMagicaMaluca() {
		
		System.out.println("Url mágica chamada");
		
	    Usuario usuario = new Usuario(); 
	    usuario.setNome("Admin");
	    usuario.setEmail("admin@casadocodigo.com.br");
	    usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
	    usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

	    usuarioDao.gravar(usuario);

	    return "Url Mágica executada";
	}
	
	
	
}

/**
 * Para Cachear o método precisamos também configurar a classe AppWebConfiguration.
 * devemos habilitar o cache com uma antoação e sobrescrever um método.
 * 
 * Esse método é um MapList, precisamos passar um valor para esse.
 * 
 */


package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@Cacheable(value="produtosHome")
public class HomeController {

	@Autowired
	ProdutoDao produtoDao;
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("home");
		List<Produto> produtos = produtoDao.listar();
		modelAndView.addObject("produtos", produtos);
		System.out.println("Entrando na Home");
		return modelAndView;
	}
}

/**
 * Para Cachear o método precisamos também configurar a classe AppWebConfiguration.
 * devemos habilitar o cache com uma antoação e sobrescrever um método.
 * 
 * Esse método é um MapList, precisamos passar um valor para esse.
 * 
 */


package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDao produtoDao;

	@Autowired
	private FileSaver fileSaver;

//	@Cacheable(value="produtosHome")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("/produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value="produtosHome", allEntries=true)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) { // se aconteceu algum erro volta para o
									// formulário
			return form(produto);
		}

		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);

		produtoDao.gravar(produto);

		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso !");

		return new ModelAndView("redirect:/produtos"); // faço isso para nao
														// ficar em cache e
														// gravar novamente ao
														// F5;
	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {

		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());

		return modelAndView;
	}

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produto", produto);
		
//		if(true) throw new RuntimeException("Exceção genérica acontecendo");
		
		return modelAndView;
	}
	
	
	@ExceptionHandler(NoResultException.class)
	public ModelAndView trataDetalheNaoEcontrado(Exception exception){
		
		System.out.println("Erro na busca de produtos");
		exception.printStackTrace();
				
		ModelAndView modelAndView = new ModelAndView("/produtos/errorProd");
		modelAndView.addObject("exception",exception);
		return modelAndView;
		
	}
	
	
	
	
//	A melhor prática é criar um ContentNegotiationViewResolver.
//	@RequestMapping("/{id}")
//	@ResponseBody
//	public Produto detalheJson(@PathVariable("id") int id) {
//		Produto produto = produtoDao.find(id);
//		return produto;
//	}

}


/**
 * OBS: Diferente da anotação de cache que precisamos apenas do atibuto Value, na CacheEvic temos que colocar
 * também que colocar também o atibuto AllEntries = true.
 * 
 * 
 * @ReponseBody
 * Retorna o corpo da resposta.
 * Quem faz a conversão para Json é o Jackson no pom.
 */


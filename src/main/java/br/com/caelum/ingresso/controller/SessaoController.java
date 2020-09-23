package br.com.caelum.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;

/*Determina que a classe ser� gerenciada pelo Spring*/
@Controller
public class SessaoController {

	@Autowired // inje��o de depend�ncia
	private SalaDao salaDAO;
	@Autowired
	private FilmeDao filmeDAO;

	/*
	 * nosso m�todo ser� acessado atrav�s da url, fazendo uma requisi��o do tipo
	 * GET Esperamos receber um parametro salaid e o m�todo retorna um objeto
	 * ModelAndView O ModelAndView passa no construtor o caminho da p�gina que
	 * retornaremos para o usu�rio - "sessao/sessao"
	 */
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId) {

		ModelAndView modelAndView = new ModelAndView("sessao/sessao");

		/*
		 * o objeto modelAndView que trasporta para a View atrav�s de uma string
		 * de identifica��o("sala" e "filmes") o objeto em si
		 */
		modelAndView.addObject("sala", salaDAO.findOne(salaId));
		modelAndView.addObject("filmes", filmeDAO.findAll());

		return modelAndView;
	}

	public SalaDao getSalaDAO() {
		return salaDAO;
	}

	public void setSalaDAO(SalaDao salaDAO) {
		this.salaDAO = salaDAO;
	}

	public FilmeDao getFilmeDAO() {
		return filmeDAO;
	}

	public void setFilmeDAO(FilmeDao filmeDAO) {
		this.filmeDAO = filmeDAO;
	}

}

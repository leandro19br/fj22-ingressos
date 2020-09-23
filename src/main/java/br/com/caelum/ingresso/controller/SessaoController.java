package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.SessaoForm;

/*Determina que a classe será gerenciada pelo Spring*/
@Controller
public class SessaoController {

	@Autowired // injeção de dependência
	private SalaDao salaDAO;
	@Autowired
	private FilmeDao filmeDAO;
	@Autowired
	private SessaoDao sessaoDAO;

	/*
	 * nosso método será acessado através da url, fazendo uma requisição do tipo
	 * GET Esperamos receber um parametro salaid e o método retorna um objeto
	 * ModelAndView O ModelAndView passa no construtor o caminho da página que
	 * retornaremos para o usuário - "sessao/sessao"
	 */
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {

		ModelAndView modelAndView = new ModelAndView("sessao/sessao");

		/*
		 * o objeto modelAndView que trasporta para a View através de uma string
		 * de identificação("sala" e "filmes") o objeto em si
		 */
		modelAndView.addObject("sala", salaDAO.findOne(salaId));
		modelAndView.addObject("filmes", filmeDAO.findAll());
		modelAndView.addObject("form", form);

		return modelAndView;
	}

	/*
	 * Metodo do verbo POST que ao chamar a url (/admin/sessao) que recebe como
	 * argumento o form com as informações selecionadas chama o método toSessao
	 * para converter os ids recebidos em objetos Sesao completo salva a sessao
	 * e faz um redirect sessãoes da sala Recebe no método um objeto
	 * BindingResult para ver se a validação resultou em erro, faz outra ação
	 */

	@PostMapping(value = "/admin/sessao")
	@Transactional
	public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {

		if (result.hasErrors()) {

			return form(form.getSalaId(), form);

		}

		Sessao sessao = form.toSessao(salaDAO, filmeDAO);
		sessaoDAO.save(sessao);
		return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");

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

	public SessaoDao getSessaoDAO() {
		return sessaoDAO;
	}

	public void setSessaoDAO(SessaoDao sessaoDAO) {
		this.sessaoDAO = sessaoDAO;
	}

}

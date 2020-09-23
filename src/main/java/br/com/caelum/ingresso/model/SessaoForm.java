package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;

/*classe responsável por representar os dados vindos do formulário*/

public class SessaoForm {

	@NotNull
	private Integer SalaId;

	@DateTimeFormat(pattern = "HH:mm")
	@NotNull
	private LocalTime horario;

	@NotNull
	private Integer filmeId;
	
	/*metodo que faz o parse de id de filme e sessão e retorna para a Sessao os objetos Filme e Sala*/
	
	public Sessao toSessao(SalaDao salaDAO, FilmeDao filmeDAO){
		
		Filme filme = filmeDAO.findOne(filmeId);
		Sala sala = salaDAO.findOne(SalaId);
		
		Sessao sessao = new Sessao(horario,filme,sala);
		
		return sessao;
	}

	public Integer getSalaId() {
		return SalaId;
	}

	public void setSalaId(Integer salaId) {
		SalaId = salaId;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}

}
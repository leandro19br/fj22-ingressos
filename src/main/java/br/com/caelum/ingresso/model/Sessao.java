package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Sessao {

	@Id
	@GeneratedValue
	private Integer id;
	
	@DateTimeFormat(pattern="HH:mm")
	@NotNull
	private LocalTime horario;
		
	@ManyToAny(metaColumn = @Column)
	private Sala sala;
	
	@ManyToAny(metaColumn = @Column)
	private Filme filme;
	
	@Deprecated
	public Sessao() {
	

	}

	public Sessao(LocalTime horario, Filme filme, Sala sala) {
		this.horario = horario;
		this.filme = filme;
		this.sala = sala;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

}
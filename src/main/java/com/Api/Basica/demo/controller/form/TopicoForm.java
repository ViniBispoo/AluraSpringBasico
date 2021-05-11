package com.Api.Basica.demo.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.Api.Basica.demo.modelo.Curso;
import com.Api.Basica.demo.modelo.Topico;
import com.Api.Basica.demo.repository.CursoRepository;
import com.sun.istack.NotNull;

public class TopicoForm {
	
	@NotBlank(message = "Title is mandatory")
	private String titulo;
	@NotBlank(message = "Message is mandatory")
	@Size(min = 5, message = "Message must be bigger than 5 chars")
	private String mensagem;
	@NotBlank(message = "Course Name is mandatory")
	private String nomeCurso;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findBynome(nomeCurso);
		return new Topico(titulo, mensagem,curso);
		
	}
	
	

}

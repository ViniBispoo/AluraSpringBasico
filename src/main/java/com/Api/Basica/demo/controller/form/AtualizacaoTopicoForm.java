package com.Api.Basica.demo.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.Api.Basica.demo.modelo.Topico;
import com.Api.Basica.demo.repository.TopicoRepository;

public class AtualizacaoTopicoForm {
	
	@NotBlank(message = "Title is mandatory")
	private String titulo;
	@NotBlank(message = "Message is mandatory")
	@Size(min = 5, message = "Message must be bigger than 5 chars")
	private String mensagem;
	
	
	public AtualizacaoTopicoForm() {
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
	
	
	
	
	
}

package com.Api.Basica.demo.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.Api.Basica.demo.modelo.Resposta;
import com.Api.Basica.demo.modelo.Topico;
import com.Api.Basica.demo.repository.TopicoRepository;

public class RespostaForm {
	
	@NotNull
	private Long idTopico;
	@NotEmpty @Size(min = 6)
	private String mensagem;
	
	public RespostaForm(Long idTopico, String mensagem) {
		super();
		this.idTopico = idTopico;
		this.mensagem = mensagem;
	}
	
	public Long getIdTopico() {
		return idTopico;
	}
	public String getMensagem() {
		return mensagem;
	}
	
	public Resposta converter(TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(idTopico);
		System.out.println(topico.getAutor().getNome());
		
		Resposta resposta = new Resposta(mensagem,topico);
		return resposta;
		
		
	}
	
}

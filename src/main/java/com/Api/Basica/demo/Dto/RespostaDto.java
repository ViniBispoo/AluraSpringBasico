package com.Api.Basica.demo.Dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.Api.Basica.demo.modelo.Resposta;
import com.Api.Basica.demo.modelo.Topico;

public class RespostaDto {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDto(Resposta resposta) {
    	this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        System.out.println("saiu aqui parte 2");
        this.nomeAutor = resposta.getAutor().getNome();
        System.out.println("saiu aqui o retorno");
    }

	public Long getId() {
		return id;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public String getNomeAutor() {
		return nomeAutor;
	}
	
	public static List<RespostaDto> converter(List<Resposta> resposta){
		return resposta.stream().map(RespostaDto::new).collect(Collectors.toList());
	}
	
}	

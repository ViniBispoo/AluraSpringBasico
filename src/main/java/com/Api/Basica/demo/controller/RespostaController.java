package com.Api.Basica.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Api.Basica.demo.Dto.RespostaDto;
import com.Api.Basica.demo.controller.form.RespostaForm;
import com.Api.Basica.demo.modelo.Resposta;
import com.Api.Basica.demo.repository.RespostaRepository;
import com.Api.Basica.demo.repository.TopicoRepository;

@RequestMapping("/Respostas")
@RestController
public class RespostaController {
	
	@Autowired
	RespostaRepository respostaRepository;
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@GetMapping
	public ResponseEntity<List<RespostaDto>> retornaResposta(){
		return ResponseEntity.ok(RespostaDto.converter(respostaRepository.findAll()));
	}
	
	@PostMapping()
	public ResponseEntity<RespostaDto> insereResposta(@RequestBody @Valid RespostaForm form, UriComponentsBuilder uriBuilder){
		Resposta resposta = form.converter(topicoRepository);
		
		respostaRepository.save(resposta);
		
		System.out.println("saiu aqui");
		
		URI uri = uriBuilder.path("/Respostas/{id}").buildAndExpand(resposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new RespostaDto(resposta));
		
	}

}

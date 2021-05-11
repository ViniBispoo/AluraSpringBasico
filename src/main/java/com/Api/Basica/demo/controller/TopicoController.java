package com.Api.Basica.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Api.Basica.demo.Dto.DetalhesTopicoDto;
import com.Api.Basica.demo.Dto.TopicoDto;
import com.Api.Basica.demo.controller.form.AtualizacaoTopicoForm;
import com.Api.Basica.demo.controller.form.TopicoForm;
import com.Api.Basica.demo.modelo.Topico;
import com.Api.Basica.demo.repository.CursoRepository;
import com.Api.Basica.demo.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public ResponseEntity<List<TopicoDto>> find(String nomeCurso){
		if (nomeCurso == null) {
			return ResponseEntity.ok(TopicoDto.converter(topicoRepository.findAll()));
		}
		else {
			return ResponseEntity.ok(TopicoDto.converter(topicoRepository.findByCurso_Nome(nomeCurso)));
		}
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder ) {
		
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
			DetalhesTopicoDto detalhe = new DetalhesTopicoDto(topico.get());
			
			return  ResponseEntity.ok(detalhe);
			
		}

		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional //Seta no banco de dados apenas setando o novo topico
	public ResponseEntity<TopicoDto> atualizaTopico(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
		Topico topicoReal = form.atualizar(id,topicoRepository);
		
		return ResponseEntity.ok(new TopicoDto(topicoReal));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaTopico(@PathVariable Long id){
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
		
			topicoRepository.deleteById(id);
		
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}

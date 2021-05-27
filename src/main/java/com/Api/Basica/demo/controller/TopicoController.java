package com.Api.Basica.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Cacheable(value = "listaDeTopicos")
	public ResponseEntity<Page<TopicoDto>> find(@RequestParam(required = false)  String nomeCurso,
			@PageableDefault(sort = "dataCriacao", direction = Direction.DESC, page = 0, size = 3) Pageable paginacao){
		
		if (nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return ResponseEntity.ok(TopicoDto.converter(topicos));
		}
		else {
			Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
			return ResponseEntity.ok(TopicoDto.converter(topicos));
		}
	}
	
	@PostMapping
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
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
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDto> atualizaTopico(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
		Topico topicoReal = form.atualizar(id,topicoRepository);
		
		return ResponseEntity.ok(new TopicoDto(topicoReal));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<?> deletaTopico(@PathVariable Long id){
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
		
			topicoRepository.deleteById(id);
		
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}

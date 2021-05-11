package com.Api.Basica.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.Basica.demo.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findBynome(String nomeCurso);

}

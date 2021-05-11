package com.Api.Basica.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Api.Basica.demo.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

	List<Topico> findByCurso_Nome(String nomeCurso);


}

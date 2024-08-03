package com.br.accenture.eBank.ebank.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.accenture.eBank.ebank.dtos.AgenciaDTO;
import com.br.accenture.eBank.ebank.services.AgenciaService;

@RestController
@RequestMapping(value = "/agencias")
public class AgenciaController {

	private AgenciaService service;
	
	@GetMapping(value = "/{id}")
	public AgenciaDTO findById(@PathVariable Long id) {
		AgenciaDTO dto = service.findById(id);
		return dto;
	}
	
	@GetMapping
	public Page<AgenciaDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
}

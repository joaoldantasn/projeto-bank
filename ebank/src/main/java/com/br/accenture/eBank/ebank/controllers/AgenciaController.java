package com.br.accenture.eBank.ebank.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.accenture.eBank.ebank.dtos.AgenciaComUsuariosDTO;
import com.br.accenture.eBank.ebank.dtos.AgenciaDTO;
import com.br.accenture.eBank.ebank.services.AgenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/agencias")
public class AgenciaController {

	@Autowired
	private AgenciaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AgenciaComUsuariosDTO> findById(@PathVariable Long id) {
		AgenciaComUsuariosDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<AgenciaDTO>> findAll(Pageable pageable){
		Page<AgenciaDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping(value = "/adicionar")
	public ResponseEntity<AgenciaDTO> insert(@Valid @RequestBody AgenciaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getIdAgencia()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

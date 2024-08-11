package com.br.accenture.eBank.ebank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.accenture.eBank.ebank.dtos.UsuarioContaDTO;
import com.br.accenture.eBank.ebank.dtos.UsuarioPutDTO;
import com.br.accenture.eBank.ebank.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping(value = "/{id}")
	@PreAuthorize("#id == principal.idUsuario") //anotacao utilizada para verificar a correspondencia do usuario
	public ResponseEntity<UsuarioContaDTO> findById(@PathVariable Long id) {
		UsuarioContaDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@PutMapping(value = "/atualizar/{id}")
	@PreAuthorize("#id == principal.idUsuario")
	public ResponseEntity<UsuarioPutDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioPutDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/deletar/{id}")
	@PreAuthorize("#id == principal.idUsuario")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}

package com.br.accenture.eBank.ebank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.dtos.UsuarioContaDTO;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public UsuarioContaDTO findById(Long id) {
		Optional<Usuario> resultado = repository.findById(id);
		Usuario usuario = resultado.get();
		UsuarioContaDTO dto = new UsuarioContaDTO(usuario);
		return dto;
	}
	
}

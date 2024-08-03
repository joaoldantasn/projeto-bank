package com.br.accenture.eBank.ebank.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.dtos.AgenciaDTO;
import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.repositories.AgenciaRepository;

@Service
public class AgenciaService {

	private AgenciaRepository repository;
	
	@Transactional(readOnly = true)
	public AgenciaDTO findById(Long id) {
		Optional<Agencia> resultado = repository.findById(id);
		Agencia agencia = resultado.get();
		AgenciaDTO dto = new AgenciaDTO(agencia);
		return dto;
	}
	
}

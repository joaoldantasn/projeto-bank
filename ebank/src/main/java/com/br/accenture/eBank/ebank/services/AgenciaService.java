package com.br.accenture.eBank.ebank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.dtos.AgenciaComUsuariosDTO;
import com.br.accenture.eBank.ebank.dtos.AgenciaDTO;
import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.repositories.AgenciaRepository;

@Service
public class AgenciaService {

	@Autowired
	private AgenciaRepository repository;
	
	
	@Transactional(readOnly = true)
	public AgenciaComUsuariosDTO findById(Long id) {
		Optional<Agencia> resultado = repository.findById(id);
		Agencia agencia = resultado.get();
		AgenciaComUsuariosDTO dto = new AgenciaComUsuariosDTO(agencia);
		return dto;
	}
	
	@Transactional(readOnly = true)
	public Page<AgenciaDTO> findAll(Pageable pageable) {
		Page<Agencia> resultado = repository.findAll(pageable);
		return resultado.map(x -> new AgenciaDTO(x));
	}
	
	
	//Salva no repositorio como Agencia porém ele recebe um DTO por isso tem que transformar
	@Transactional
	public AgenciaDTO insert(AgenciaDTO dto) {
		Agencia entity = new Agencia();
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new AgenciaDTO(entity);
	}
	
	@Transactional
	public AgenciaDTO update(Long id, AgenciaDTO dto) {
		Agencia entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		
		return new AgenciaDTO(entity);
	}
	
	@Transactional
	private void copyDtoToEntity(AgenciaDTO dto, Agencia entity) {
		entity.setCodAgencia(dto.getCodAgencia());
		entity.setEndereco(dto.getEndereco());
		entity.setTelefone(dto.getTelefone());
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}

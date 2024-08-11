package com.br.accenture.eBank.ebank.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.dtos.UsuarioContaDTO;
import com.br.accenture.eBank.ebank.dtos.UsuarioPutDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Transacao;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.repositories.EnderecoRepository;
import com.br.accenture.eBank.ebank.repositories.TransacaoRepository;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public UsuarioContaDTO findById(Long id) {
		Optional<Usuario> resultado = repository.findById(id);
		Usuario usuario = resultado.get();
		UsuarioContaDTO dto = new UsuarioContaDTO(usuario);
		return dto;
	}

	@Transactional
	public UsuarioPutDTO update(Long id, UsuarioPutDTO dto) {
	    Usuario entity = repository.getReferenceById(id);

	    // Atualiza o usuário com os dados do DTO
	    copyDtoToEntity(dto, entity);

	    // Salva as alterações do usuário
	    entity = repository.save(entity);

	    return new UsuarioPutDTO(entity);
	}

	@Transactional
	private void copyDtoToEntity(UsuarioPutDTO dto, Usuario entity) {
	    
	    entity.setCpf(dto.getCpf());
	    entity.setNomeUsuario(dto.getNomeUsuario());
	    entity.setTelefone(dto.getTelefone());

	    
	    if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
	        entity.setSenha(passwordEncoder.encode(dto.getSenha()));
	    }

	    
	    if (dto.getEndereco() != null) {
	        Endereco endereco = dto.getEndereco();
	        if (endereco.getIdEndereco() == null) {
	           
	            endereco = enderecoRepository.save(endereco);
	        } else {
	            
	            endereco = enderecoRepository.findById(endereco.getIdEndereco())
	                            .orElseThrow(() -> new EntityNotFoundException("Endereco não encontrado"));
	        }
	        entity.setEndereco(endereco);
	    }
	}

	@Transactional
	public void delete(Long id) {
		Usuario user = repository.findById(id).orElseThrow(() -> new ContaNaoEncontradaException("Conta Não encontrada"));

		Set<Conta> contas = new HashSet<>(user.getContas());
		List<Transacao> transacoes = transacaoRepository.findByContaOrContaDestinatario(contas);

		transacaoRepository.deleteAll(transacoes);
		repository.deleteById(id);
	}


	/*
	 * Autentica se o usuario está acessando os dados correspondentes ao do seu Login
	 */
	public boolean isUserAuthorized(Authentication authentication, Long idUsuario) {
	    // Recupera o usuário autenticado pelo CPF
	    Usuario currentUser = (Usuario) repository.findByCpf(authentication.getName());

	    // Verifica se o ID do usuário autenticado corresponde ao ID solicitado
	    return currentUser.getIdUsuario().equals(idUsuario);
	}


}

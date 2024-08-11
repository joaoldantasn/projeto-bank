package com.br.accenture.eBank.ebank.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.dtos.UsuarioContaDTO;
import com.br.accenture.eBank.ebank.dtos.UsuarioDTO;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Transactional(readOnly = true)
	public UsuarioContaDTO findById(Long id) {
		Optional<Usuario> resultado = repository.findById(id);
		Usuario usuario = resultado.get();
		UsuarioContaDTO dto = new UsuarioContaDTO(usuario);
		return dto;
	}

	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		Usuario entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);

		entity = repository.save(entity);

		return new UsuarioDTO(entity);
	}

	@Transactional
	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setCpf(dto.getCpf());
		entity.setNomeUsuario(dto.getNomeUsuario());
		entity.setTelefone(dto.getTelefone());
		entity.setSenha(dto.getSenha());
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

package com.br.accenture.eBank.ebank.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		UserDetails usuario = repository.findByCpf(cpf);
		if (usuario == null) {
			throw new UsernameNotFoundException("User not found with CPF: " + cpf);
		}
		return usuario;
	}


}
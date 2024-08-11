package com.br.accenture.eBank.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.accenture.eBank.ebank.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// consulta usuario pelo login
	UserDetails findByCpf(String cpf);


}

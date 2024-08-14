package com.br.accenture.eBank.ebank.dtos.auth;

import java.util.Set;

import com.br.accenture.eBank.ebank.dtos.conta.ContaDTO;
import com.br.accenture.eBank.ebank.dtos.endereco.EnderecoDTO;
import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;

public record RegisterDTO(
	    String cpf,
	    String nomeUsuario,
	    String telefone,
	    String senha,
	    UserRoles role,
	    Long idAgencia,  // Adicionando o idAgencia
	    EnderecoDTO endereco,
	    Set<ContaDTO> contas) {
	}
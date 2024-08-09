package com.br.accenture.eBank.ebank.dtos.auth;

import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;

public record RegisterDTO(String cpf, String nomeUsuario, String telefone, String senha, UserRoles role) {

}

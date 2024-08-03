package com.br.accenture.eBank.ebank.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Usuario;

public class AgenciaComUsuariosDTO {

	private Long idAgencia;
	private int codAgencia;
	private String endereco;
	private String telefone;
	private Set<UsuarioDTO> usuarios = new HashSet<>();
	
	public AgenciaComUsuariosDTO() {
		
	}

	
	public AgenciaComUsuariosDTO(Long idAgencia, int codAgencia, String endereco, String telefone,
			Set<Usuario> usuarios) {
		this.idAgencia = idAgencia;
		this.codAgencia = codAgencia;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public AgenciaComUsuariosDTO(Agencia entity) {
		idAgencia = entity.getIdAgencia();
		codAgencia = entity.getCodAgencia();
		endereco = entity.getEndereco();
		telefone = entity.getTelefone();
		usuarios = entity.getUsuarios().stream().map(UsuarioDTO::new).collect(Collectors.toSet());
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public int getCodAgencia() {
		return codAgencia;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getTelefone() {
		return telefone;
	}


	public Set<UsuarioDTO> getUsuarios() {
		return usuarios;
	}
	
	

}

package com.br.accenture.eBank.ebank.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Endereco;

public class AgenciaComUsuariosDTO {

	private Long idAgencia;
	private int codAgencia;
	private Endereco endereco;
	private String telefone;
	private Set<UsuarioDTO> usuarios = new HashSet<>();
	
	public AgenciaComUsuariosDTO() {
		
	}

	public AgenciaComUsuariosDTO(Long idAgencia, int codAgencia, Endereco endereco, String telefone,
			Set<UsuarioDTO> usuarios) {
		super();
		this.idAgencia = idAgencia;
		this.codAgencia = codAgencia;
		this.endereco = endereco;
		this.telefone = telefone;
		this.usuarios = usuarios;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public String getTelefone() {
		return telefone;
	}


	public Set<UsuarioDTO> getUsuarios() {
		return usuarios;
	}
	
	

}

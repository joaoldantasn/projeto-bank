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
	private Set<UsuarioEContaDTO> usuarios = new HashSet<>();
	
	public AgenciaComUsuariosDTO() {
		
	}

	public AgenciaComUsuariosDTO(Long idAgencia, int codAgencia, Endereco endereco, String telefone,
			Set<UsuarioEContaDTO> usuarios) {
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
		usuarios = entity.getUsuarios().stream().map(UsuarioEContaDTO::new).collect(Collectors.toSet());
	}

	public Long getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public int getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(int codAgencia) {
		this.codAgencia = codAgencia;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<UsuarioEContaDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioEContaDTO> usuarios) {
		this.usuarios = usuarios;
	}

	
	

}

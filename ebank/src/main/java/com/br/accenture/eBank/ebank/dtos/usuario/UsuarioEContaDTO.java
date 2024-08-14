package com.br.accenture.eBank.ebank.dtos.usuario;

import java.util.HashSet;
import java.util.Set;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Usuario;

public class UsuarioEContaDTO {

	Long idUsuario;
	String cpf;
	String nomeUsuario;
	private Set<Conta> contas = new HashSet<>();
	
	public UsuarioEContaDTO() {
		
	}
	
	

	public UsuarioEContaDTO(Long idUsuario, String cpf, String nomeUsuario, Set<Conta> contas) {
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nomeUsuario = nomeUsuario;
		this.contas = contas;
	}
	
	public UsuarioEContaDTO(Usuario entity) {
		idUsuario = entity.getIdUsuario();
		cpf = entity.getCpf();
		nomeUsuario = entity.getNomeUsuario();
		contas = entity.getContas();
	}



	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public Set<Conta> getContas() {
		return contas;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}



	public void setContas(Set<Conta> contas) {
		this.contas = contas;
	}
	
	
	
	
	
}

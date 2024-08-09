package com.br.accenture.eBank.ebank.dtos;

import java.util.HashSet;
import java.util.Set;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;

public class UsuarioContaDTO {

    private Long idUsuario;
    private String cpf;
    private String nomeUsuario;
    private String telefone;
    private UserRoles role;
    private Endereco endereco;
    private Set<Conta> contas = new HashSet<>();
    
    public UsuarioContaDTO() {
    	
    }

	public UsuarioContaDTO(Long idUsuario, String cpf, String nomeUsuario, String telefone, UserRoles role,
			Endereco endereco, Set<Conta> contas) {
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nomeUsuario = nomeUsuario;
		this.telefone = telefone;
		this.role = role;
		this.endereco = endereco;
		this.contas = contas;
	}

	public UsuarioContaDTO(Usuario entity) {
		idUsuario = entity.getIdUsuario();
		cpf = entity.getCpf();
		nomeUsuario = entity.getNomeUsuario();
		telefone = entity.getTelefone();
		role = entity.getRole();
		endereco = entity.getEndereco();
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

	public String getTelefone() {
		return telefone;
	}

	public UserRoles getRole() {
		return role;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Set<Conta> getContas() {
		return contas;
	}
    
    
	
}

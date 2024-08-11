package com.br.accenture.eBank.ebank.dtos;

import com.br.accenture.eBank.ebank.entities.Usuario;

public class UsuarioDTO {

	private Long idUsuario;
	private String cpf;
	private String nomeUsuario;
	private String telefone;
	private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String cpf, String nomeUsuario, String telefone, String senha) {
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nomeUsuario = nomeUsuario;
		this.telefone = telefone;
		this.senha = senha;
	}

	public UsuarioDTO(Usuario entity) {
        idUsuario = entity.getIdUsuario();
        cpf = entity.getCpf();
        nomeUsuario = entity.getNomeUsuario();
        telefone = entity.getTelefone();
        senha = entity.getSenha();
    }

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	

}
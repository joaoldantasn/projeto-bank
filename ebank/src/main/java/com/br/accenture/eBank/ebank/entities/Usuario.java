package com.br.accenture.eBank.ebank.entities;

import com.br.accenture.eBank.ebank.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;
	private String cpf;
	private String nomeUsuario;
	private String telefone;
	private String senha;
	
	@ManyToOne
    @JoinColumn(name = "endereco_id")
	@JsonIgnore
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "agencia_id")
	private Agencia agencia;
	
	@OneToMany(mappedBy = "usuario")
	private Set<Conta>contas = new HashSet<>();
	
	public Usuario() {
		
	}

	public Usuario(Long idUsuario, String cpf, String nomeUsuario, String telefone, String senha, Endereco endereco,
			Agencia agencia) {
		super();
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nomeUsuario = nomeUsuario;
		this.telefone = telefone;
		this.senha = senha;
		this.endereco = endereco;
		this.agencia = agencia;
	}

	public Usuario(UsuarioDTO usuario) {
		super();
		this.idUsuario = usuario.getIdUsuario();
		this.cpf = usuario.getCpf();
		this.nomeUsuario = usuario.getNomeUsuario();
		this.telefone = usuario.getTelefone();
		this.senha = usuario.getSenha();

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


	public Agencia getAgencia() {
		return agencia;
	}


	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}


	public Set<Conta> getContas() {
		return contas;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idUsuario, other.idUsuario);
	}


	// Adiciona uma conta ao usuário
	public void addConta(Conta conta) {
		contas.add(conta);
		conta.setUsuario(this);
	}
	
}

package com.br.accenture.eBank.ebank.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_agencia")
public class Agencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgencia;
	private int codAgencia;
	//nomeagencia
	private String endereco;
	private String telefone;
	
	@OneToMany(mappedBy = "agencia")
	private Set<Usuario>usuarios = new HashSet<>();
	
	public Agencia() {
		
	}

	public Agencia(Long idAgencia, int codAgencia, String endereco, String telefone) {
		this.idAgencia = idAgencia;
		this.codAgencia = codAgencia;
		this.endereco = endereco;
		this.telefone = telefone;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAgencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agencia other = (Agencia) obj;
		return Objects.equals(idAgencia, other.idAgencia);
	}

	// Adiciona um usuário à agência
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
		usuario.setAgencia(this);
	}
	
}

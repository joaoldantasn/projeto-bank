package com.br.accenture.eBank.ebank.entities;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_agencia")
public class Agencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgencia;
	private int codAgencia;
	private String telefone;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="endereco_id")
	@JsonManagedReference
	private Endereco endereco;

	@OneToMany(mappedBy = "agencia", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<Usuario> usuarios = new HashSet<>();

	public Agencia() {

	}

	public Agencia(Long idAgencia, int codAgencia, String telefone, Endereco endereco) {
		this.idAgencia = idAgencia;
		this.codAgencia = codAgencia;
		this.telefone = telefone;
		this.endereco = endereco;
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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}


}
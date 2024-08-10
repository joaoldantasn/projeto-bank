package com.br.accenture.eBank.ebank.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_endereco")
@JsonIgnoreProperties({"tb_agencia"})
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEndereco;
	private String cep;
	private String logradouro;
	private String cidade;
	private String bairro;
	private String numero;
	
	@OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL, orphanRemoval = true)
	private Agencia agencia;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "endereco")
	@JsonIgnore
	private Set<Usuario> usuarios;
	
	public Endereco() {
		
	}

	public Endereco(Long idEndereco, String cep, String logradouro, String cidade, String bairro, String numero,
			Agencia agencia) {
		this.idEndereco = idEndereco;
		this.cep = cep;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.bairro = bairro;
		this.numero = numero;
		this.agencia = agencia;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}	
	
}

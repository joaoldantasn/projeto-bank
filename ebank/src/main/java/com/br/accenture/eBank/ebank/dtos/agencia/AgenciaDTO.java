package com.br.accenture.eBank.ebank.dtos.agencia;

import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Endereco;

public class AgenciaDTO {

	private Long idAgencia;
	private int codAgencia;
	private Endereco endereco;
	private String telefone;
	
	public AgenciaDTO() {
		
	}
	
	
	public AgenciaDTO(Long idAgencia, int codAgencia, Endereco endereco, String telefone) {
		super();
		this.idAgencia = idAgencia;
		this.codAgencia = codAgencia;
		this.endereco = endereco;
		this.telefone = telefone;
	}


	public AgenciaDTO(Agencia entity) {
		idAgencia = entity.getIdAgencia();
		codAgencia = entity.getCodAgencia();
		endereco = entity.getEndereco();
		telefone = entity.getTelefone();
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

	
	
}

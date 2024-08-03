package com.br.accenture.eBank.ebank.dtos;

import com.br.accenture.eBank.ebank.entities.Agencia;

public class AgenciaDTO {

	private Long idAgencia;
	private int codAgencia;
	private String endereco;
	private String telefone;
	
	public AgenciaDTO() {
		
	}

	public AgenciaDTO(Long idAgencia, int codAgencia, String endereco, String telefone) {
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

	public int getCodAgencia() {
		return codAgencia;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getTelefone() {
		return telefone;
	}
	
}

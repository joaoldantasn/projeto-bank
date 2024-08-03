package com.br.accenture.eBank.ebank.entities;

import java.time.Instant;

import com.br.accenture.eBank.ebank.entities.enums.Operacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_extrato")
public class Extrato {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idExtrato;
	private Instant dataHoraMov;
	private Operacao operacao;
	
	@OneToOne
	@MapsId
	private Conta conta;
	
	public Extrato() {
		
	}

	public Extrato(Long idExtrato, Instant dataHoraMov, Operacao operacao, Conta conta) {
		this.idExtrato = idExtrato;
		this.dataHoraMov = dataHoraMov;
		this.operacao = operacao;
		this.conta = conta;
	}

	public Long getIdExtrato() {
		return idExtrato;
	}

	public void setIdExtrato(Long idExtrato) {
		this.idExtrato = idExtrato;
	}

	public Instant getDataHoraMov() {
		return dataHoraMov;
	}

	public void setDataHoraMov(Instant dataHoraMov) {
		this.dataHoraMov = dataHoraMov;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
	
	
	
}

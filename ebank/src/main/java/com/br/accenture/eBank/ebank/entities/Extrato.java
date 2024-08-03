package com.br.accenture.eBank.ebank.entities;

import java.time.Instant;
import java.util.Objects;

import com.br.accenture.eBank.ebank.entities.enums.Operacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@Enumerated(EnumType.STRING) // Mapeia o enum como um valor ordinal (número)
	private Operacao operacao;
	
	@OneToOne
	@JoinColumn(name="conta_id")
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

	@Override
	public int hashCode() {
		return Objects.hash(idExtrato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extrato other = (Extrato) obj;
		return Objects.equals(idExtrato, other.idExtrato);
	}

}

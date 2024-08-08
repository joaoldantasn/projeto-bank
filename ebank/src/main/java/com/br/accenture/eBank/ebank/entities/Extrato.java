package com.br.accenture.eBank.ebank.entities;

import java.time.Instant;
import java.util.Objects;

import com.br.accenture.eBank.ebank.entities.enums.Operacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "tb_extrato")
public class Extrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExtrato;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Instant dataHoraMov;

	@Enumerated(EnumType.STRING)
	private Operacao operacao;

	private Double valor;

	private String descricao;

	@ManyToOne
	@JoinColumn(name = "conta_id")
	@NotNull
	private Conta conta;

	@ManyToOne
	@JoinColumn(name = "conta_destinatario_id")
	private Conta contaDestinatario;

	public Extrato() {}

	public Extrato(Long idExtrato, Instant dataHoraMov, Operacao operacao, Double valor, String descricao, Conta conta, Conta contaDestinatario) {
		this.idExtrato = idExtrato;
		this.dataHoraMov = dataHoraMov;
		this.operacao = operacao;
		this.valor = valor;
		this.descricao = descricao;
		this.conta = conta;
		this.contaDestinatario = contaDestinatario;
	}

	// Getters and Setters
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getContaDestinatario() {
		return contaDestinatario;
	}

	public void setContaDestinatario(Conta contaDestinatario) {
		this.contaDestinatario = contaDestinatario;
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

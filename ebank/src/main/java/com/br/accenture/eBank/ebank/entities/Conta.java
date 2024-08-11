package com.br.accenture.eBank.ebank.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.br.accenture.eBank.ebank.entities.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "tb_conta")
public class Conta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idConta;
	@NotNull(message = "O número da conta não pode ser nulo")
    @PositiveOrZero(message = "O número da conta deve ser positivo ou zero")
	private int numeroConta;
	private BigDecimal saldo;
	private boolean ativa;
	private String chavePix;
	@Enumerated(EnumType.STRING)  // Mapeia o enum como um valor ordinal (número)
	private TipoConta tipoConta;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;

	public Conta() {
		
	}
	
    public Conta(Long idConta, int numeroConta, BigDecimal saldo, Boolean ativa, String chavePix, TipoConta tipoConta) {
        this.idConta = idConta;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.ativa = ativa;
        this.chavePix = chavePix;
        this.tipoConta = tipoConta;
    }

	public Conta(Long idConta, int numeroConta, BigDecimal saldo, boolean ativa, String chavePix, TipoConta tipoConta,
			Usuario usuario) {
		this.idConta = idConta;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.ativa = ativa;
		this.chavePix = chavePix;
		this.tipoConta = tipoConta;
		this.usuario = usuario;

	}



	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public String getChavePix() {
		return chavePix;
	}

	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(idConta, other.idConta);
	}

}

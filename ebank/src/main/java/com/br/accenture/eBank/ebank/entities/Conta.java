package com.br.accenture.eBank.ebank.entities;

import java.math.BigDecimal;

import com.br.accenture.eBank.ebank.entities.enums.TipoConta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_conta")
public class Conta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idConta;
	private int numeroConta;
	private BigDecimal saldo;
	private boolean ativa;
	private String chavePix;
	private TipoConta tipoConta;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToOne(mappedBy = "conta", cascade = CascadeType.ALL)
	private Extrato extrato;
	
	public Conta() {
		
	}
	
	

	public Conta(Long idConta, int numeroConta, BigDecimal saldo, boolean ativa, String chavePix, TipoConta tipoConta,
			Usuario usuario, Extrato extrato) {
		this.idConta = idConta;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.ativa = ativa;
		this.chavePix = chavePix;
		this.tipoConta = tipoConta;
		this.usuario = usuario;
		this.extrato = extrato;
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
	
	
}

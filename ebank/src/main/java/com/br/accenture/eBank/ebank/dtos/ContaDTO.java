package com.br.accenture.eBank.ebank.dtos;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Extrato;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;

import java.math.BigDecimal;

public class ContaDTO {

    private Long idConta;
    private int numeroConta;
    private BigDecimal saldo;
    private boolean ativa;
    private String chavePix;
    private TipoConta tipoConta;
    private Usuario usuario;

    public ContaDTO() {
    }

    public ContaDTO(Conta conta) {
        this.idConta = conta.getIdConta();
        this.numeroConta = conta.getNumeroConta();
        this.saldo = conta.getSaldo();
        this.ativa = conta.isAtiva();
        this.chavePix = conta.getChavePix();
        this.tipoConta = conta.getTipoConta();
        this.usuario = conta.getUsuario();
    }


    public ContaDTO(Long idConta, int numeroConta, BigDecimal saldo, boolean ativa, String chavePix, TipoConta tipoConta, Usuario usuario, Extrato extrato) {
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


}

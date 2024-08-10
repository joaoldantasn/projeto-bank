package com.br.accenture.eBank.ebank.dtos;

import com.br.accenture.eBank.ebank.entities.enums.Operacao;

import java.math.BigDecimal;
import java.time.Instant;

public class TransacaoDTO {


    private Long id;

    private Instant dataHora;

    private Operacao tipo;

    private BigDecimal valor;

    private String descricao;


    private ContaDTO conta;

    private ContaDTO contaDestinatario;

    public TransacaoDTO() {
    }

    public TransacaoDTO(Long id, Instant dataHora, Operacao tipo, BigDecimal valor, String descricao, ContaDTO conta, ContaDTO contaDestinatario) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.conta = conta;
        this.contaDestinatario = contaDestinatario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataHora() {
        return dataHora;
    }

    public void setDataHora(Instant dataHora) {
        this.dataHora = dataHora;
    }

    public Operacao getTipo() {
        return tipo;
    }

    public void setTipo(Operacao tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ContaDTO getConta() {
        return conta;
    }

    public void setConta(ContaDTO conta) {
        this.conta = conta;
    }

    public ContaDTO getContaDestinatario() {
        return contaDestinatario;
    }

    public void setContaDestinatario(ContaDTO contaDestinatario) {
        this.contaDestinatario = contaDestinatario;
    }
}

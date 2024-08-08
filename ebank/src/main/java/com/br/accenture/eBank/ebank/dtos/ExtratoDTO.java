package com.br.accenture.eBank.ebank.dtos;

import java.time.Instant;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Extrato;
import com.br.accenture.eBank.ebank.entities.enums.Operacao;

public class ExtratoDTO {

    private Long idExtrato;
    private Instant dataHoraMov;
    private Operacao operacao;
    private Double valor;
    private String descricao;
    private Conta conta;
    private Conta contaDestinatario;

    public ExtratoDTO() {}

    public ExtratoDTO(Extrato extrato) {
        this.idExtrato = extrato.getIdExtrato();
        this.dataHoraMov = extrato.getDataHoraMov();
        this.operacao = extrato.getOperacao();
        this.valor = extrato.getValor();
        this.descricao = extrato.getDescricao();
        this.conta = extrato.getConta();
        this.contaDestinatario = extrato.getContaDestinatario();
    }


    public Long getIdExtrato() { return idExtrato; }
    public void setIdExtrato(Long idExtrato) { this.idExtrato = idExtrato; }

    public Instant getDataHoraMov() { return dataHoraMov; }
    public void setDataHoraMov(Instant dataHoraMov) { this.dataHoraMov = dataHoraMov; }

    public Operacao getOperacao() { return operacao; }
    public void setOperacao(Operacao operacao) { this.operacao = operacao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }

    public Conta getContaDestinatario() { return contaDestinatario; }
    public void setContaDestinatario(Conta contaDestinatario) { this.contaDestinatario = contaDestinatario; }
}

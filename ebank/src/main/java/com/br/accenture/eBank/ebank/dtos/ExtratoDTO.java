package com.br.accenture.eBank.ebank.dtos;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;

import java.time.Instant;
import java.util.List;

public class ExtratoDTO {

    private Instant dataHoraGeracao;
    private String nomeUsuario;
    private String descricao;
    private int numAgencia;
    private int numConta;

    private List<TransacaoExtratoDTO> transacoes;
    private Instant periodoInicio;
    private Instant periodoFim;

    public ExtratoDTO() {
    }

    public ExtratoDTO(Instant dataHoraMov,String nomeUsuario, String descricao, int numAgencia, int numConta, List<TransacaoExtratoDTO> transacoes, Instant periodoInicio, Instant periodoFim) {
        this.dataHoraGeracao = dataHoraMov;
        this.nomeUsuario = nomeUsuario;
        this.descricao = descricao;
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.transacoes = transacoes;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
    }

    public Instant getDataHoraGeracao() {
        return dataHoraGeracao;
    }

    public void setDataHoraGeracao(Instant dataHoraGeracao) {
        this.dataHoraGeracao = dataHoraGeracao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public List<TransacaoExtratoDTO> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<TransacaoExtratoDTO> transacoes) {
        this.transacoes = transacoes;
    }

    public Instant getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(Instant periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public Instant getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(Instant periodoFim) {
        this.periodoFim = periodoFim;
    }
}

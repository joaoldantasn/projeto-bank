package com.br.accenture.eBank.ebank.entities;

import com.br.accenture.eBank.ebank.entities.enums.Operacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Instant dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Operacao tipo;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestinatario;


    public Transacao() {
    }

    public Transacao(Long idTransacao, Instant dataHora, BigDecimal valor, Conta conta, Conta contaDestinatario) {
        this.id = idTransacao;
        this.dataHora = dataHora;
        this.valor = valor;
        this.conta = conta;
        this.contaDestinatario = contaDestinatario;
    }

    public Long getIdTransacao() {
        return id;
    }

    public void setIdTransacao(Long idTransacao) {
        this.id = idTransacao;
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

}

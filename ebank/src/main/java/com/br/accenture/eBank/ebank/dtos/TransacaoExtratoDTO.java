package com.br.accenture.eBank.ebank.dtos;

import com.br.accenture.eBank.ebank.entities.enums.Operacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

public class TransacaoExtratoDTO {
    private Long id;


    private Instant dataHora;


    private Operacao tipo;

    private BigDecimal valor;

    private String descricao;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContaDTO contaDestinatario;

    public TransacaoExtratoDTO() {
    }

    public TransacaoExtratoDTO(Long id, Instant dataHora, Operacao tipo, BigDecimal valor, String descricao, ContaDTO conta, ContaDTO contaDestinatario) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
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

    public ContaDTO getContaDestinatario() {
        return contaDestinatario;
    }

    public void setContaDestinatario(ContaDTO contaDestinatario) {
        this.contaDestinatario = contaDestinatario;
    }

    @JsonSerialize
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", id);
        gen.writeStringField("dataHora", dataHora.toString());
        gen.writeStringField("tipo", String.valueOf(tipo));
        gen.writeNumberField("valor", valor);
        gen.writeStringField("descricao", descricao);
        if ("TRANSFERENCIA".equals(tipo)) {
            gen.writeObjectField("contaDestinatario", contaDestinatario);
        }
        gen.writeEndObject();
    }
}

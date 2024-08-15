package com.br.accenture.eBank.ebank.dtos.transacao;

import com.br.accenture.eBank.ebank.dtos.conta.ContaResponseDTO;
import com.br.accenture.eBank.ebank.entities.Transacao;
import com.br.accenture.eBank.ebank.entities.enums.Operacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransacaoDTO {

    private Long id;
    private Instant dataHora;
    private Operacao tipo;
    private BigDecimal valor;
    private ContaResponseDTO conta;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContaResponseDTO contaDestinatario;


    public TransacaoDTO(Transacao transacao) {
        this.id = transacao.getIdTransacao();
        this.dataHora = transacao.getDataHora();
        this.tipo = transacao.getTipo();
        this.valor = transacao.getValor();
        this.conta = transacao.getConta() != null ? new ContaResponseDTO(transacao.getConta()) : null;
        this.contaDestinatario = transacao.getContaDestinatario() != null ? new ContaResponseDTO(transacao.getContaDestinatario()) : null;
    }
}

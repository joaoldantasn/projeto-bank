package com.br.accenture.eBank.ebank.dtos.transacao;

import com.br.accenture.eBank.ebank.dtos.conta.ContaResponseDTO;
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
public class TransacaoExtratoDTO {

    private Long id;
    private Instant dataHora;
    private Operacao tipo;
    private BigDecimal valor;
    private String descricao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContaResponseDTO contaDestinatario;

}

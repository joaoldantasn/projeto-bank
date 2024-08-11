package com.br.accenture.eBank.ebank.dtos;

import com.br.accenture.eBank.ebank.entities.enums.Operacao;
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
    private ContaResponseDTO contaDestinatario;


}

package com.br.accenture.eBank.ebank.dtos.transacao;
import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExtratoDTO {

    private Instant dataHoraGeracao;
    private String nomeUsuario;
    private String descricao;
    private int numAgencia;
    private int numConta;
    private List<TransacaoExtratoDTO> transacoes;
    private Instant periodoInicio;
    private Instant periodoFim;


}

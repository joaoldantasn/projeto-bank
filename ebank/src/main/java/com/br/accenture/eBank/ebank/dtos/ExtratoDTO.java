package com.br.accenture.eBank.ebank.dtos;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

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

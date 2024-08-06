package com.br.accenture.eBank.ebank.dtos;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Extrato;
import com.br.accenture.eBank.ebank.entities.enums.Operacao;

import java.time.Instant;

public class ExtratoDTO {

    private Long idExtrato;

    private Instant dataHoraMov;

    private Operacao operacao;

    private Conta conta;

    public ExtratoDTO(Extrato extrato) {

    }
}

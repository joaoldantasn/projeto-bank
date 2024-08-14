package com.br.accenture.eBank.ebank.dtos.conta;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContaResponseDTO {

    private Long idConta;
    private int numeroConta;
    private BigDecimal saldo;
    private boolean ativa;
    private String chavePix;
    private TipoConta tipoConta;



    public ContaResponseDTO(Conta conta) {
        this.idConta = conta.getIdConta();
        this.numeroConta = conta.getNumeroConta();
        this.saldo = conta.getSaldo();
        this.ativa = conta.isAtiva();
        this.chavePix = conta.getChavePix();
        this.tipoConta = conta.getTipoConta();

    }

}

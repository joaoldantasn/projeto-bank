package com.br.accenture.eBank.ebank.dtos.conta;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContaExtratoDTO {

    private int numeroConta;
    private TipoConta tipoConta;



    public ContaExtratoDTO(Conta conta) {
        this.numeroConta = conta.getNumeroConta();
        this.tipoConta = conta.getTipoConta();

    }
}

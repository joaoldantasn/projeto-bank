package com.br.accenture.eBank.ebank.exceptions;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(BigDecimal valor) {
        super("Saldo insuficiente para a transação de valor R$" + valor + ".");
    }
}

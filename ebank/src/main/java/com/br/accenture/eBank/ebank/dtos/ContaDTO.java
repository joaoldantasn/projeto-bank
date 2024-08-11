package com.br.accenture.eBank.ebank.dtos;

import java.math.BigDecimal;

import com.br.accenture.eBank.ebank.entities.enums.TipoConta;

public record ContaDTO(int numeroConta, BigDecimal saldo, boolean ativa, String chavePix, TipoConta tipoConta) {
}

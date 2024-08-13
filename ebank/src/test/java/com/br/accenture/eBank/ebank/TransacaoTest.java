package com.br.accenture.eBank.ebank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;

class TransacaoTest {

	@Test
    void testConstructorWithAllParameters() {
        // Arrange
        Long idTransacao = 1L;
        Instant dataHora = Instant.now();
        BigDecimal valor = new BigDecimal("100.00");
        Conta conta = new Conta();  // Configure o objeto Conta conforme necessário
        Conta contaDestinatario = new Conta();  // Configure o objeto Conta conforme necessário

        // Act
        Transacao transacao = new Transacao(idTransacao, dataHora, valor, conta, contaDestinatario);

        // Assert
        assertEquals(idTransacao, transacao.getIdTransacao());
        assertEquals(dataHora, transacao.getDataHora());
        assertEquals(valor, transacao.getValor());
        assertEquals(conta, transacao.getConta());
        assertEquals(contaDestinatario, transacao.getContaDestinatario());
    }

    @Test
    void testSetIdTransacao() {
        // Arrange
        Transacao transacao = new Transacao();
        Long expectedId = 1L;

        // Act
        transacao.setIdTransacao(expectedId);

        // Assert
        assertEquals(expectedId, transacao.getIdTransacao());
    }
    
    @Test
    void testGetConta() {
        // Arrange
        Conta conta = new Conta();  // Configure o objeto Conta conforme necessário
        Transacao transacao = new Transacao();
        transacao.setConta(conta);

        // Act
        Conta result = transacao.getConta();

        // Assert
        assertEquals(conta, result);
    }
}

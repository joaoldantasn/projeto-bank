package com.br.accenture.eBank.ebank.conta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;

class ContaTest {

	 @Test
	    void testConstructorWithAllParameters() {
	        // Arrange
	        Long idConta = 1L;
	        int numeroConta = 123456;
	        BigDecimal saldo = new BigDecimal("1000.00");
	        boolean ativa = true;
	        String chavePix = "chavePix123";
	        TipoConta tipoConta = TipoConta.CORRENTE; // Supondo que você tenha um enum TipoConta
	        Usuario usuario = new Usuario(); // Configure o objeto Usuario conforme necessário

	        // Act
	        Conta conta = new Conta(idConta, numeroConta, saldo, ativa, chavePix, tipoConta, usuario);

	        // Assert
	        assertEquals(idConta, conta.getIdConta());
	        assertEquals(numeroConta, conta.getNumeroConta());
	        assertEquals(saldo, conta.getSaldo());
	        assertEquals(ativa, conta.isAtiva());
	        assertEquals(chavePix, conta.getChavePix());
	        assertEquals(tipoConta, conta.getTipoConta());
	        assertEquals(usuario, conta.getUsuario());
	    }
	 
	 @Test
	    void testEquals() {
	        // Arrange
	        Conta conta1 = new Conta(1L, 123456, new BigDecimal("1000.00"), true, "chavePix123", TipoConta.CORRENTE, new Usuario());
	        Conta conta2 = new Conta(1L, 123456, new BigDecimal("1000.00"), true, "chavePix123", TipoConta.CORRENTE, new Usuario());
	        Conta conta3 = new Conta(2L, 654321, new BigDecimal("2000.00"), false, "chavePix456", TipoConta.POUPANCA, new Usuario());

	        // Act & Assert
	        assertTrue(conta1.equals(conta2), "Contas com o mesmo ID devem ser iguais");
	        assertFalse(conta1.equals(conta3), "Contas com IDs diferentes devem ser diferentes");
	        assertFalse(conta1.equals(null), "Uma conta não deve ser igual a null");
	        assertFalse(conta1.equals(new Object()), "Uma conta não deve ser igual a um objeto de outra classe");
	    }

}

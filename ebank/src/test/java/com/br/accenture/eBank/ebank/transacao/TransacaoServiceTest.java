package com.br.accenture.eBank.ebank.transacao;

import com.br.accenture.eBank.ebank.dtos.transacao.TransacaoExtratoDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;
import com.br.accenture.eBank.ebank.entities.enums.Operacao;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.exceptions.SaldoInsuficienteException;
import com.br.accenture.eBank.ebank.repositories.ContaRepository;
import com.br.accenture.eBank.ebank.repositories.TransacaoRepository;
import com.br.accenture.eBank.ebank.services.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransacaoServiceTest {

    @Autowired
    @InjectMocks
    private TransacaoService transacaoService;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    private Conta conta1;
    private Conta conta2;
    private Conta conta3;


    @BeforeEach
    public void setUp() {

        conta1 = new Conta();
        conta1.setSaldo(BigDecimal.valueOf(1000));
        conta1.setNumeroConta(111111);
        contaRepository.save(conta1);

        conta2 = new Conta();
        conta2.setSaldo(BigDecimal.valueOf(500));
        conta1.setNumeroConta(222222);
        contaRepository.save(conta2);

        conta3 = new Conta();
        conta3.setSaldo(BigDecimal.valueOf(500));
        conta3.setNumeroConta(33333);
        contaRepository.save(conta3);
    }

    @Test
    public void testSacarComSaldoSuficiente() {
        transacaoService.sacar(conta1.getIdConta(), BigDecimal.valueOf(200));

        Conta contaAtualizada = contaRepository.findById(conta1.getIdConta()).orElse(null);
        assertNotNull(contaAtualizada);
        assertEquals(0,BigDecimal.valueOf(800).compareTo(contaAtualizada.getSaldo()));

        List<Transacao> transacoes = transacaoRepository.findByContaAndDataHoraBetween(
                conta1, Instant.MIN, Instant.MAX);
        assertEquals(1, transacoes.size());

        Transacao transacao = transacoes.get(0);
        assertEquals(Operacao.SAQUE, transacao.getTipo());
        assertEquals(0,BigDecimal.valueOf(-200).compareTo(transacao.getValor()) );
    }

    @Test
    public void testSacarComSaldoInsuficiente() {
        Conta conta = new Conta();
        conta.setSaldo(BigDecimal.valueOf(100));
        contaRepository.save(conta);

        Exception exception = assertThrows(SaldoInsuficienteException.class, () -> {
            transacaoService.sacar(conta.getIdConta(), BigDecimal.valueOf(200));
        });
        assertEquals("Saldo insuficiente para a transação de valor R$200.", exception.getMessage());
    }

    @Test
    public void testSacarContaNaoEncontrada() {
        assertThrows(ContaNaoEncontradaException.class, () ->
                transacaoService.sacar(99L, BigDecimal.valueOf(100.00)));
    }

    @Test
    public void testDepositar() {
        transacaoService.depositar(conta3.getIdConta(), BigDecimal.valueOf(300));

        Conta contaAtualizada = contaRepository.findById(conta3.getIdConta()).orElse(null);
        assertNotNull(contaAtualizada);
        assertEquals(0,BigDecimal.valueOf(800).compareTo(contaAtualizada.getSaldo()));

        List<Transacao> transacoes = transacaoRepository.findByContaAndDataHoraBetween(
                conta3, Instant.MIN, Instant.MAX);
        assertEquals(1, transacoes.size());

        Transacao transacao = transacoes.get(0);
        assertEquals(Operacao.DEPOSITO, transacao.getTipo());
        assertEquals(0,BigDecimal.valueOf(300).compareTo(transacao.getValor()));
    }

    @Test
    public void testDepositarContaNaoEncontrada() {
        assertThrows(ContaNaoEncontradaException.class, () ->
                transacaoService.depositar(99L, BigDecimal.valueOf(100.00)));
    }

    @Test
    public void testTransferirViaPix() {
        conta2.setChavePix("chave-pix-exemplo");
        contaRepository.save(conta2);

        transacaoService.transferirViaPix(conta1.getIdConta(), "chave-pix-exemplo", BigDecimal.valueOf(250));

        Conta contaOrigemAtualizada = contaRepository.findById(conta1.getIdConta()).orElse(null);
        Conta contaDestinoAtualizada = contaRepository.findById(conta2.getIdConta()).orElse(null);
        assertNotNull(contaOrigemAtualizada);
        assertNotNull(contaDestinoAtualizada);
        assertEquals(0,BigDecimal.valueOf(750).compareTo(contaOrigemAtualizada.getSaldo()));
        assertEquals(0,BigDecimal.valueOf(750).compareTo(contaDestinoAtualizada.getSaldo()));

        List<Transacao> transacoes = transacaoRepository.findByContaAndDataHoraBetween(
                conta1, Instant.MIN, Instant.MAX);
        assertEquals(1, transacoes.size());

        Transacao transacao = transacoes.get(0);
        assertEquals(Operacao.TRANSFERENCIA_PIX, transacao.getTipo());
        assertEquals(0,BigDecimal.valueOf(-250).compareTo(transacao.getValor()));
        assertNotNull(transacao.getContaDestinatario());
        assertEquals(contaDestinoAtualizada.getIdConta(), transacao.getContaDestinatario().getIdConta());
    }

    @Test
    public void testBuscarTransacoesPorPeriodo() {
        transacaoService.sacar(conta1.getIdConta(), BigDecimal.valueOf(100));
        transacaoService.transferir(conta1.getIdConta(), conta2.getNumeroConta(), BigDecimal.valueOf(100));
        Instant now = Instant.now();
        List<TransacaoExtratoDTO> transacoes = transacaoService.buscarTransacoesPorPeriodo(
                conta1, now.minusSeconds(10000), now);

        assertEquals(2, transacoes.size());
        TransacaoExtratoDTO transacao1 = transacoes.get(0);
        TransacaoExtratoDTO transacao2 = transacoes.get(1);
        assertEquals(0,BigDecimal.valueOf(-100).compareTo(transacao1.getValor()));
        assertEquals(0,BigDecimal.valueOf(-100).compareTo(transacao2.getValor()));
    }

    @Test
    public void testTransferirContaOrigemNaoEcontrada() {
        Conta contaDestino = new Conta();
        contaDestino.setSaldo(BigDecimal.valueOf(500));
        contaRepository.save(contaDestino);

        Exception exception = assertThrows(ContaNaoEncontradaException.class, () -> {
            transacaoService.transferir(99L, contaDestino.getNumeroConta(), BigDecimal.valueOf(200));
        });
        assertEquals("Conta de origem não encontrada", exception.getMessage());
    }

    @Test
    public void testTransferirContaDestinoNaoEcontrada() {
        Conta contaOrigem = new Conta();
        contaOrigem.setSaldo(BigDecimal.valueOf(500));
        contaRepository.save(contaOrigem);

        Exception exception = assertThrows(ContaNaoEncontradaException.class, () -> {
            transacaoService.transferir(contaOrigem.getIdConta(), 11111, BigDecimal.valueOf(200));
        });
        assertEquals("Conta de destino não encontrada", exception.getMessage());
    }

    @Test
    public void testTransferirComSaldoInsuficiente() {
        Conta contaOrigem = new Conta();
        contaOrigem.setSaldo(BigDecimal.valueOf(100));
        contaRepository.save(contaOrigem);

        Conta contaDestino = new Conta();
        contaDestino.setSaldo(BigDecimal.valueOf(500));
        contaRepository.save(contaDestino);

        Exception exception = assertThrows(SaldoInsuficienteException.class, () -> {
            transacaoService.transferir(contaOrigem.getIdConta(), contaDestino.getNumeroConta(), BigDecimal.valueOf(200));
        });
        assertEquals("Saldo insuficiente para a transação de valor R$200.", exception.getMessage());
    }

    @Test
    public void testTransferirViaPixComChavePixInvalida() {
        Conta contaOrigem = new Conta();
        contaOrigem.setSaldo(BigDecimal.valueOf(1000));
        contaOrigem.setChavePix("chave-pix-exemplo");
        contaRepository.save(contaOrigem);

        Exception exception = assertThrows(ContaNaoEncontradaException.class, () -> {
            transacaoService.transferirViaPix(contaOrigem.getIdConta(), "chave-pix-invalida", BigDecimal.valueOf(250));
        });
        assertEquals("Conta de destino não encontrada", exception.getMessage());
    }

    @Test
    public void testTransferirViaPixComContaOrigiemInválida() {

        Exception exception = assertThrows(ContaNaoEncontradaException.class, () -> {
            transacaoService.transferirViaPix(99L, "chave-pix-invalida", BigDecimal.valueOf(250));
        });
        assertEquals("Conta de origem não encontrada", exception.getMessage());
    }

    @Test
    public void testTransferirViaPixSaldoInsuficiente() {
        conta2.setChavePix("chave-pix-válida");
        contaRepository.save(conta2);
        Exception exception = assertThrows(SaldoInsuficienteException.class, () -> {
            transacaoService.transferirViaPix(conta1.getIdConta(), "chave-pix-válida", BigDecimal.valueOf(5000));
        });

        assertEquals("Saldo insuficiente para a transação de valor R$5000.", exception.getMessage());

    }
}

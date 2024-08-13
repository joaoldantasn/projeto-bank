package com.br.accenture.eBank.ebank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;
import com.br.accenture.eBank.ebank.entities.enums.Operacao;
import com.br.accenture.eBank.ebank.repositories.ContaRepository;
import com.br.accenture.eBank.ebank.repositories.TransacaoRepository;

@DataJpaTest
@Transactional
public class TransacaoRepositoryTest {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    private Conta contaOrigem;
    private Conta contaDestino;

    @BeforeEach
    public void setUp() {
        // Configuração inicial para os testes
        contaOrigem = new Conta();
        contaOrigem.setNumeroConta(12345678);
        contaOrigem.setSaldo(BigDecimal.valueOf(1000));
        contaOrigem.setAtiva(true);
        contaRepository.save(contaOrigem);

        contaDestino = new Conta();
        contaDestino.setNumeroConta(65432100);
        contaDestino.setSaldo(BigDecimal.valueOf(500));
        contaDestino.setAtiva(true);
        contaRepository.save(contaDestino);
    }

    @Test
    public void testSaveTransacao_ShouldPersistSuccessfully() {
        // Cria e salva uma Transacao
        Transacao transacao = new Transacao();
        transacao.setDataHora(Instant.now());
        transacao.setTipo(Operacao.TRANSFERENCIA);
        transacao.setValor(BigDecimal.valueOf(100));
        transacao.setConta(contaOrigem);
        transacao.setContaDestinatario(contaDestino);

        Transacao savedTransacao = transacaoRepository.save(transacao);

        // Verifica se a transação foi salva corretamente
        assertThat(savedTransacao).isNotNull();
        assertThat(savedTransacao.getIdTransacao()).isNotNull();
        assertThat(savedTransacao.getValor()).isEqualByComparingTo(BigDecimal.valueOf(100));
    }



    @Test
    public void testFindByContaOrContaDestinatario_ShouldReturnCorrectResults() {
        // Cria e salva uma transação
        Transacao transacao = new Transacao();
        transacao.setDataHora(Instant.now());
        transacao.setTipo(Operacao.TRANSFERENCIA);
        transacao.setValor(BigDecimal.valueOf(100));
        transacao.setConta(contaOrigem);
        transacao.setContaDestinatario(contaDestino);
        transacaoRepository.save(transacao);

        // Busca por conta origem e destinatário
        List<Transacao> transacoes = transacaoRepository.findByContaOrContaDestinatario(Set.of(contaOrigem, contaDestino));

        // Verifica se a transação está presente na busca
        assertThat(transacoes).isNotEmpty();
        assertThat(transacoes.get(0).getValor()).isEqualByComparingTo(BigDecimal.valueOf(100));
    }

    @Test
    public void testSaveTransacao_WithInvalidTipo_ShouldThrowDataIntegrityViolationException() {
        // Cria uma transação com tipo inválido
        Transacao transacao = new Transacao();
        transacao.setDataHora(Instant.now());
        transacao.setTipo(null); // Tipo nulo
        transacao.setValor(BigDecimal.valueOf(100));
        transacao.setConta(contaOrigem);
        transacao.setContaDestinatario(contaDestino);

        // Verifica se uma exceção de integridade de dados é lançada
        try {
            transacaoRepository.save(transacao);
            fail("Esperava-se uma exceção DataIntegrityViolationException");
        } catch (DataIntegrityViolationException e) {
            // Verifica a mensagem de erro específica do SQL
            assertThat(e.getMessage()).contains("NULL not allowed for column \"TIPO\"");
        }
    }
    


}
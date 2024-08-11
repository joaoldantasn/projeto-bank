package com.br.accenture.eBank.ebank.repositories;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

        List<Transacao> findByContaAndDataHoraBetween(Conta conta, Instant periodoInicio, Instant periodoFim);

        @Query("SELECT t FROM Transacao t WHERE t.conta IN :contas OR t.contaDestinatario IN :contas")
        List<Transacao> findByContaOrContaDestinatario(@Param("contas") Set<Conta> contas);

}

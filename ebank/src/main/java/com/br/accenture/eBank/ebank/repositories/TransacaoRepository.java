package com.br.accenture.eBank.ebank.repositories;

import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

        List<Transacao> findByContaAndDataHoraBetween(Conta conta, Instant periodoInicio, Instant periodoFim);

}

package com.br.accenture.eBank.ebank.repositories;

import com.br.accenture.eBank.ebank.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.accenture.eBank.ebank.entities.Extrato;

import java.util.Optional;

public interface ExtratoRepository extends JpaRepository<Extrato, Long>{

    Optional<Extrato>  findByConta_IdConta(Long contaId);
}

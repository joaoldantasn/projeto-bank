package com.br.accenture.eBank.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.accenture.eBank.ebank.entities.Conta;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long>{



}

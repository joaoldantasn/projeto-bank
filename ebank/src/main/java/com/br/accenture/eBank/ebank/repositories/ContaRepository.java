package com.br.accenture.eBank.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.accenture.eBank.ebank.entities.Conta;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long>{

    Optional<Conta> findContaByChavePix(String chave);

    Optional<Conta> findFirstByNumeroConta(int numero);

}

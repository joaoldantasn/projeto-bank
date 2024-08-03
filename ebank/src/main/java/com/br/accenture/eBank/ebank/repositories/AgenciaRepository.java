package com.br.accenture.eBank.ebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.accenture.eBank.ebank.entities.Usuario;

public interface AgenciaRepository extends JpaRepository<Usuario, Long>{

}

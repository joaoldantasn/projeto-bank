package com.br.accenture.eBank.ebank.agencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.repositories.AgenciaRepository;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
public class AgenciaRepositoryTest {

    @Autowired
    private AgenciaRepository agenciaRepository;

    @BeforeEach
    public void setUp() {
       
    }

    @Test
    public void testFindById() {
      
        Optional<Agencia> agencia = agenciaRepository.findById(1L);
        assertThat(agencia).isPresent();
        assertThat(agencia.get().getCodAgencia()).isEqualTo(1234);
    }

    @Test
    public void testFindAll() {
       
        Iterable<Agencia> agencias = agenciaRepository.findAll();
        assertThat(agencias).hasSize(3); 
    }

    @Test
    public void testSave() {
     
        Agencia newAgencia = new Agencia();
        newAgencia.setCodAgencia(9999);
        newAgencia.setTelefone("9123456789");
        Endereco endereco = new Endereco();
        endereco.setCep("00000-000");
        endereco.setLogradouro("Rua Nova");
        endereco.setCidade("Cidade Nova");
        endereco.setBairro("Bairro Novo");
        endereco.setNumero("1");
        newAgencia.setEndereco(endereco);

        Agencia savedAgencia = agenciaRepository.save(newAgencia);

        assertThat(savedAgencia).isNotNull();
        assertThat(savedAgencia.getIdAgencia()).isNotNull();
        assertThat(savedAgencia.getCodAgencia()).isEqualTo(9999);
    }

    @Test
    public void testDelete() {
        
        agenciaRepository.deleteById(1L);
        Optional<Agencia> deletedAgencia = agenciaRepository.findById(1L);
        assertThat(deletedAgencia).isNotPresent();
    }
    
    @Test
    public void testFindById_Fail() {
        
        Optional<Agencia> agencia = agenciaRepository.findById(999L);
        assertThat(agencia).isNotPresent();  
    }

    @Test
    public void testSave_Fail() {
        
        Agencia newAgencia = new Agencia();
        newAgencia.setCodAgencia(-1); 
        newAgencia.setTelefone("123"); 
        Endereco endereco = new Endereco();
        endereco.setCep("00000-000");
        endereco.setLogradouro("Rua Nova");
        endereco.setCidade("Cidade Nova");
        endereco.setBairro("Bairro Novo");
        endereco.setNumero("1");
        newAgencia.setEndereco(endereco);

        try {
            agenciaRepository.save(newAgencia);
            fail("Deveria ter lançado uma exceção devido aos dados inválidos");
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
          
        }
    }
    
    @Test
    public void testFindAll_Fail() {
        
        Iterable<Agencia> agencias = agenciaRepository.findAll();
        assertThat(agencias).hasSize(3); 
    }
    

    @Test
    public void testDelete_Fail() {
        
        Agencia agencia = new Agencia();
        agencia.setCodAgencia(9999); 
        agencia.setTelefone("1234567890");
        Endereco endereco = new Endereco();
        endereco.setCep("99999-999");
        endereco.setLogradouro("Rua Inexistente");
        endereco.setCidade("Cidade Inexistente");
        endereco.setBairro("Bairro Inexistente");
        endereco.setNumero("999");
        agencia.setEndereco(endereco);

        Agencia savedAgencia = agenciaRepository.save(agencia); 
        agenciaRepository.deleteById(savedAgencia.getIdAgencia());
        
        Optional<Agencia> deletedAgencia = agenciaRepository.findById(savedAgencia.getIdAgencia());
        assertThat(deletedAgencia).isNotPresent(); 
    }
}
package com.br.accenture.eBank.ebank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.repositories.EnderecoRepository;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
@Transactional
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    public void setUp() {
       
    }

    @Test
    public void testSaveEndereco_Success() {
       
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setNumero("123");

      
        Endereco savedEndereco = enderecoRepository.save(endereco);

       
        assertThat(savedEndereco).isNotNull();
        assertThat(savedEndereco.getIdEndereco()).isNotNull();
        assertThat(savedEndereco.getCep()).isEqualTo("12345-678");
    }

    @Test
    public void testFindById_Success() {
      
        Endereco endereco = new Endereco();
        endereco.setCep("23456-789");
        endereco.setLogradouro("Avenida Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setNumero("456");

        Endereco savedEndereco = enderecoRepository.save(endereco);
        Long enderecoId = savedEndereco.getIdEndereco();

       
        Optional<Endereco> foundEndereco = enderecoRepository.findById(enderecoId);

        
        assertThat(foundEndereco).isPresent();
        assertThat(foundEndereco.get().getCep()).isEqualTo("23456-789");
    }

    @Test
    public void testDeleteById_Success() {
        
        Endereco endereco = new Endereco();
        endereco.setCep("34567-890");
        endereco.setLogradouro("Travessa Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setNumero("789");

        Endereco savedEndereco = enderecoRepository.save(endereco);
        Long enderecoId = savedEndereco.getIdEndereco();

        
        assertThat(enderecoRepository.findById(enderecoId)).isPresent();

        
        enderecoRepository.deleteById(enderecoId);

        
        assertThat(enderecoRepository.findById(enderecoId)).isNotPresent();
    }

    @Test
    public void testUpdateEndereco_Success() {
        Endereco endereco = new Endereco();
        endereco.setCep("45678-901");
        endereco.setLogradouro("Rua Atual");
        endereco.setCidade("Cidade Atual");
        endereco.setBairro("Bairro Atual");
        endereco.setNumero("101");

        Endereco savedEndereco = enderecoRepository.save(endereco);
        Long enderecoId = savedEndereco.getIdEndereco();

       
        savedEndereco.setLogradouro("Rua Atualizada");
        savedEndereco.setCidade("Cidade Atualizada");
        Endereco updatedEndereco = enderecoRepository.save(savedEndereco);

        
        assertThat(updatedEndereco.getLogradouro()).isEqualTo("Rua Atualizada");
        assertThat(updatedEndereco.getCidade()).isEqualTo("Cidade Atualizada");
    }
    
    @Test
    public void testSaveEndereco_WithInvalidCep_ShouldThrowConstraintViolationException() {
      
        Endereco endereco = new Endereco();
        endereco.setCep("1234-567"); 
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setNumero("123");

    
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> enderecoRepository.save(endereco));
    }

    @Test
    public void testFindById_WithNonExistentId_ShouldReturnEmptyOptional() {
        
        Long nonExistentId = 999L;
        Optional<Endereco> foundEndereco = enderecoRepository.findById(nonExistentId);


        assertThat(foundEndereco).isNotPresent();
    }
    
    @Test
    public void testSaveEndereco_WithNullCep_ShouldThrowConstraintViolationException() {
      
        Endereco endereco = new Endereco();
        endereco.setCep(null); // CEP nulo
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setNumero("123");

       
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> enderecoRepository.save(endereco));
    }
    
    @Test
    public void testSaveEndereco_WithEmptyCep_ShouldThrowConstraintViolationException() {
        
        Endereco endereco = new Endereco();
        endereco.setCep(""); 
        endereco.setLogradouro("Rua Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setNumero("123");

     
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> enderecoRepository.save(endereco));
    }
}

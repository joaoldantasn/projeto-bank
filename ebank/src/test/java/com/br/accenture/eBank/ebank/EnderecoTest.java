package com.br.accenture.eBank.ebank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;

class EnderecoTest {

	 @Test
	    void testConstructorWithAllParameters() {
	        // Arrange
	        Long idEndereco = 1L;
	        String cep = "12345-678";
	        String logradouro = "Rua Exemplo";
	        String cidade = "Cidade Exemplo";
	        String bairro = "Bairro Exemplo";
	        String numero = "123";
	        Agencia agencia = new Agencia();  
	        Set<Usuario> usuarios = new HashSet<>();  

	        
	        Endereco endereco = new Endereco(idEndereco, cep, logradouro, cidade, bairro, numero, agencia, usuarios);

	        assertEquals(idEndereco, endereco.getIdEndereco());
	        assertEquals(cep, endereco.getCep());
	        assertEquals(logradouro, endereco.getLogradouro());
	        assertEquals(cidade, endereco.getCidade());
	        assertEquals(bairro, endereco.getBairro());
	        assertEquals(numero, endereco.getNumero());
	        assertEquals(agencia, endereco.getAgencia());
	        assertEquals(usuarios, endereco.getUsuarios());
	    }

	    @Test
	    void testSetAgencia() {
	        
	        Endereco endereco = new Endereco();
	        Agencia agencia = new Agencia();  

	        
	        endereco.setAgencia(agencia);

	        
	        assertEquals(agencia, endereco.getAgencia());
	    }

	    @Test
	    void testGetBairro() {
	        
	        Endereco endereco = new Endereco();
	        String bairro = "Bairro Exemplo";
	        endereco.setBairro(bairro);

	        
	        String result = endereco.getBairro();

	       
	        assertEquals(bairro, result);
	    }

	    @Test
	    void testGetNumero() {
	        
	        Endereco endereco = new Endereco();
	        String numero = "123";
	        endereco.setNumero(numero);

	       
	        String result = endereco.getNumero();

	       
	        assertEquals(numero, result);
	    }

	    @Test
	    void testGetAgencia() {
	        
	        Endereco endereco = new Endereco();
	        Agencia agencia = new Agencia();  
	        endereco.setAgencia(agencia);

	        
	        Agencia result = endereco.getAgencia();

	        
	        assertEquals(agencia, result);
	    }

	    @Test
	    void testGetUsuarios() {
	        
	        Endereco endereco = new Endereco();
	        Set<Usuario> usuarios = new HashSet<>();  
	        endereco.setUsuarios(usuarios);

	        
	        Set<Usuario> result = endereco.getUsuarios();

	        
	        assertEquals(usuarios, result);
	    }

}

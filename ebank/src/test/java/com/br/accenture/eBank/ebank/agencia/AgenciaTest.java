package com.br.accenture.eBank.ebank.agencia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;

class AgenciaTest {

	 @Test
	    void testSetIdAgencia() {
	        
	        Agencia agencia = new Agencia();
	        Long idAgencia = 1L;

	        
	        agencia.setIdAgencia(idAgencia);

	       
	        assertEquals(idAgencia, agencia.getIdAgencia());
	    }

	    @Test
	    void testGetEndereco() {
	        
	        Agencia agencia = new Agencia();
	        Endereco endereco = new Endereco();  
	        agencia.setEndereco(endereco);

	        
	        Endereco result = agencia.getEndereco();

	        
	        assertEquals(endereco, result);
	    }

	    @Test
	    void testGetTelefone() {
	        
	        Agencia agencia = new Agencia();
	        String telefone = "1234567890";
	        agencia.setTelefone(telefone);

	        
	        String result = agencia.getTelefone();

	       
	        assertEquals(telefone, result);
	    }

	    @Test
	    void testGetUsuarios() {
	        
	        Agencia agencia = new Agencia();
	        Set<Usuario> usuarios = new HashSet<>(); 
	        agencia.setUsuarios(usuarios);

	        
	        Set<Usuario> result = agencia.getUsuarios();

	       
	        assertEquals(usuarios, result);
	    }

	    @Test
	    void testConstructorWithAllParameters() {
	        
	        Long idAgencia = 1L;
	        int codAgencia = 123;
	        String telefone = "1234567890";
	        Endereco endereco = new Endereco();  

	       
	        Agencia agencia = new Agencia(idAgencia, codAgencia, telefone, endereco);

	       
	        assertEquals(idAgencia, agencia.getIdAgencia());
	        assertEquals(codAgencia, agencia.getCodAgencia());
	        assertEquals(telefone, agencia.getTelefone());
	        assertEquals(endereco, agencia.getEndereco());
	    }

	    @Test
	    void testSetEndereco() {
	        
	        Agencia agencia = new Agencia();
	        Endereco endereco = new Endereco();  

	       
	        agencia.setEndereco(endereco);

	        
	        assertEquals(endereco, agencia.getEndereco());
	    }

	    @Test
	    void testSetUsuarios() {
	       
	        Agencia agencia = new Agencia();
	        Set<Usuario> usuarios = new HashSet<>();  

	       
	        agencia.setUsuarios(usuarios);

	        
	        assertEquals(usuarios, agencia.getUsuarios());
	    }

}

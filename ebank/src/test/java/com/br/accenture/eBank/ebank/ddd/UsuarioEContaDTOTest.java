package com.br.accenture.eBank.ebank.ddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.dtos.UsuarioEContaDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Usuario;

class UsuarioEContaDTOTest {

	 @Test
	    public void whenAllArgumentsConstructorIsUsed_thenDTOIsInitializedCorrectly() {
	        Set<Conta> contas = new HashSet<>();
	        contas.add(new Conta());

	        UsuarioEContaDTO dto = new UsuarioEContaDTO(1L, "12345678900", "Test User", contas);

	        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
	        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
	        assertEquals(contas, dto.getContas(), "O conjunto de contas deve ser o mesmo que o fornecido.");
	    }
	 
	 @Test
	    public void whenDefaultConstructorIsUsed_thenFieldsShouldBeInitializedWithDefaults() {
	        UsuarioEContaDTO dto = new UsuarioEContaDTO();

	        assertNull(dto.getIdUsuario(), "O ID do usuário deve ser nulo.");
	        assertNull(dto.getCpf(), "O CPF do usuário deve ser nulo.");
	        assertNull(dto.getNomeUsuario(), "O nome do usuário deve ser nulo.");
	        assertTrue(dto.getContas().isEmpty(), "O conjunto de contas deve estar vazio.");
	    }
	 
	 @Test
	    public void whenEntityConstructorIsUsed_thenDTOIsInitializedCorrectly() {
	        Usuario usuario = new Usuario();
	        usuario.setIdUsuario(1L);
	        usuario.setCpf("12345678900");
	        usuario.setNomeUsuario("Test User");
	        usuario.setContas(new HashSet<>(Collections.singletonList(new Conta())));

	        UsuarioEContaDTO dto = new UsuarioEContaDTO(usuario);

	        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
	        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
	        assertEquals(usuario.getContas(), dto.getContas(), "O conjunto de contas deve ser o mesmo que o do usuário.");
	    }
	 
	 @Test
	    public void whenSettersAreUsed_thenGettersReturnCorrectValues() {
	        UsuarioEContaDTO dto = new UsuarioEContaDTO();
	        Set<Conta> contas = new HashSet<>();
	        contas.add(new Conta());
	        dto.setIdUsuario(1L);
	        dto.setCpf("12345678900");
	        dto.setNomeUsuario("Test User");
	        dto.setContas(contas);

	        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
	        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
	        assertEquals(contas, dto.getContas(), "O conjunto de contas deve ser o mesmo que o fornecido.");
	    }

}

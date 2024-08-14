package com.br.accenture.eBank.ebank.ddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.dtos.usuario.UsuarioContaDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;

class UsuarioContaDTOTest {

	@Test
    public void whenAllArgumentsConstructorIsUsed_thenDTOIsInitializedCorrectly() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345678");
        Set<Conta> contas = new HashSet<>();
        contas.add(new Conta());

        UsuarioContaDTO dto = new UsuarioContaDTO(1L, "12345678900", "Test User", "123456789", UserRoles.USER, endereco, contas);

        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
        assertEquals("123456789", dto.getTelefone(), "O telefone do usuário deve ser 123456789.");
        assertEquals(UserRoles.USER, dto.getRole(), "A role do usuário deve ser USER.");
        assertEquals(endereco, dto.getEndereco(), "O endereço deve ser o mesmo que o fornecido.");
        assertEquals(contas, dto.getContas(), "O conjunto de contas deve ser o mesmo que o fornecido.");
    }
	
	@Test
    public void whenDefaultConstructorIsUsed_thenFieldsShouldBeInitializedWithDefaults() {
        UsuarioContaDTO dto = new UsuarioContaDTO();

        assertNull(dto.getIdUsuario(), "O ID do usuário deve ser nulo.");
        assertNull(dto.getCpf(), "O CPF do usuário deve ser nulo.");
        assertNull(dto.getNomeUsuario(), "O nome do usuário deve ser nulo.");
        assertNull(dto.getTelefone(), "O telefone do usuário deve ser nulo.");
        assertNull(dto.getRole(), "A role do usuário deve ser nula.");
        assertNull(dto.getEndereco(), "O endereço deve ser nulo.");
        assertTrue(dto.getContas().isEmpty(), "O conjunto de contas deve estar vazio.");
    }
	
	 @Test
	    public void whenSettersAreUsed_thenGettersReturnCorrectValues() {
	        UsuarioContaDTO dto = new UsuarioContaDTO();

	        Endereco endereco = new Endereco();
	        endereco.setCep("12345678");
	        Set<Conta> contas = new HashSet<>();
	        contas.add(new Conta());

	        dto.setIdUsuario(1L);
	        dto.setCpf("12345678900");
	        dto.setNomeUsuario("Test User");
	        dto.setTelefone("123456789");
	        dto.setRole(UserRoles.USER);
	        dto.setEndereco(endereco);
	        dto.setContas(contas);

	        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
	        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
	        assertEquals("123456789", dto.getTelefone(), "O telefone do usuário deve ser 123456789.");
	        assertEquals(UserRoles.USER, dto.getRole(), "A role do usuário deve ser USER.");
	        assertEquals(endereco, dto.getEndereco(), "O endereço deve ser o mesmo que o fornecido.");
	        assertEquals(contas, dto.getContas(), "O conjunto de contas deve ser o mesmo que o fornecido.");
	    }
	 
	 @Test
	    public void whenContasIsSet_thenGetContasReturnsCorrectValue() {
	        UsuarioContaDTO dto = new UsuarioContaDTO();
	        Set<Conta> contas = new HashSet<>();
	        contas.add(new Conta());
	        dto.setContas(contas);

	        assertEquals(contas, dto.getContas(), "O conjunto de contas deve ser o mesmo que o fornecido.");
	    }

}

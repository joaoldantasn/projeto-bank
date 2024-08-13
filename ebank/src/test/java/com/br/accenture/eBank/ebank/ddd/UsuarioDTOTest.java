package com.br.accenture.eBank.ebank.ddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.dtos.UsuarioDTO;
import com.br.accenture.eBank.ebank.entities.Usuario;

class UsuarioDTOTest {

	@Test
    public void whenUsuarioEntityIsPassedToConstructor_thenDTOIsInitializedCorrectly() {
        // Criando uma entidade Usuario
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setCpf("12345678900");
        usuario.setNomeUsuario("Test User");
        usuario.setTelefone("123456789");
        usuario.setSenha("password");

        // Criando o DTO usando o construtor com a entidade
        UsuarioDTO dto = new UsuarioDTO(usuario);

        // Verificando se o DTO foi inicializado corretamente
        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
        assertEquals("123456789", dto.getTelefone(), "O telefone do usuário deve ser 123456789.");
        assertEquals("password", dto.getSenha(), "A senha do usuário deve ser 'password'.");
    }
	
	 @Test
	    public void whenConstructorIsUsed_thenDTOIsInitializedCorrectly() {
	        // Criando o DTO usando o construtor com parâmetros
	        UsuarioDTO dto = new UsuarioDTO(1L, "12345678900", "Test User", "123456789", "password");

	        // Verificando se o DTO foi inicializado corretamente
	        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
	        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
	        assertEquals("123456789", dto.getTelefone(), "O telefone do usuário deve ser 123456789.");
	        assertEquals("password", dto.getSenha(), "A senha do usuário deve ser 'password'.");
	    }
	 
	 @Test
	    public void whenSettersAreUsed_thenGettersReturnCorrectValues() {
	        UsuarioDTO dto = new UsuarioDTO();

	        dto.setIdUsuario(1L);
	        dto.setCpf("12345678900");
	        dto.setNomeUsuario("Test User");
	        dto.setTelefone("123456789");
	        dto.setSenha("password");

	        assertEquals(1L, dto.getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals("12345678900", dto.getCpf(), "O CPF do usuário deve ser 12345678900.");
	        assertEquals("Test User", dto.getNomeUsuario(), "O nome do usuário deve ser 'Test User'.");
	        assertEquals("123456789", dto.getTelefone(), "O telefone do usuário deve ser 123456789.");
	        assertEquals("password", dto.getSenha(), "A senha do usuário deve ser 'password'.");
	    }
	 
	 @Test
	    public void whenDefaultConstructorIsUsed_thenFieldsShouldBeNull() {
	        UsuarioDTO dto = new UsuarioDTO();

	        assertNull(dto.getIdUsuario(), "O ID do usuário deve ser nulo.");
	        assertNull(dto.getCpf(), "O CPF do usuário deve ser nulo.");
	        assertNull(dto.getNomeUsuario(), "O nome do usuário deve ser nulo.");
	        assertNull(dto.getTelefone(), "O telefone do usuário deve ser nulo.");
	        assertNull(dto.getSenha(), "A senha do usuário deve ser nula.");
	    }
	 
	 @Test
	    public void whenSenhaIsSet_thenGetSenhaReturnsCorrectValue() {
	        UsuarioDTO dto = new UsuarioDTO();
	        dto.setSenha("password");

	        assertEquals("password", dto.getSenha(), "A senha do usuário deve ser 'password'.");
	    }

}

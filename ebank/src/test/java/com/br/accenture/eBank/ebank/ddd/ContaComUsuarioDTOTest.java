package com.br.accenture.eBank.ebank.ddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.br.accenture.eBank.ebank.dtos.conta.ContaComUsuarioDTO;
import com.br.accenture.eBank.ebank.dtos.usuario.UsuarioDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;

class ContaComUsuarioDTOTest {

	 @Test
	    public void whenContaConstructorIsUsed_thenDTOIsInitializedCorrectly() {
	        Usuario usuario = new Usuario();
	        usuario.setIdUsuario(1L);
	        usuario.setCpf("12345678900");
	        usuario.setNomeUsuario("Test User");
	        usuario.setTelefone("123456789");
	        
	        Conta conta = new Conta();
	        conta.setIdConta(1L);
	        conta.setNumeroConta(123456);
	        conta.setSaldo(BigDecimal.valueOf(1000.00));
	        conta.setAtiva(true);
	        conta.setChavePix("chave-pix");
	        conta.setTipoConta(TipoConta.CORRENTE);
	        conta.setUsuario(usuario);

	        ContaComUsuarioDTO dto = new ContaComUsuarioDTO(conta);

	        assertEquals(1L, dto.getIdConta(), "O ID da conta deve ser 1L.");
	        assertEquals(123456, dto.getNumeroConta(), "O número da conta deve ser 123456.");
	        assertEquals(BigDecimal.valueOf(1000.00), dto.getSaldo(), "O saldo da conta deve ser 1000.00.");
	        assertTrue(dto.isAtiva(), "A conta deve estar ativa.");
	        assertEquals("chave-pix", dto.getChavePix(), "A chave Pix deve ser 'chave-pix'.");
	        assertEquals(TipoConta.CORRENTE, dto.getTipoConta(), "O tipo da conta deve ser CHECKING.");
	        assertNotNull(dto.getUsuario(), "O usuário não deve ser nulo.");
	        assertEquals(usuario.getIdUsuario(), dto.getUsuario().getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals(usuario.getCpf(), dto.getUsuario().getCpf(), "O CPF do usuário deve ser 12345678900.");
	    }
	 
	 @Test
	    public void whenAllArgumentsConstructorIsUsed_thenDTOIsInitializedCorrectly() {
	        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "12345678900", "Test User", "123456789", "senha");

	        ContaComUsuarioDTO dto = new ContaComUsuarioDTO(
	            1L,
	            123456,
	            BigDecimal.valueOf(1000.00),
	            true,
	            "chave-pix",
	            TipoConta.CORRENTE,
	            usuarioDTO
	        );

	        assertEquals(1L, dto.getIdConta(), "O ID da conta deve ser 1L.");
	        assertEquals(123456, dto.getNumeroConta(), "O número da conta deve ser 123456.");
	        assertEquals(BigDecimal.valueOf(1000.00), dto.getSaldo(), "O saldo da conta deve ser 1000.00.");
	        assertTrue(dto.isAtiva(), "A conta deve estar ativa.");
	        assertEquals("chave-pix", dto.getChavePix(), "A chave Pix deve ser 'chave-pix'.");
	        assertEquals(TipoConta.CORRENTE, dto.getTipoConta(), "O tipo da conta deve ser CHECKING.");
	        assertNotNull(dto.getUsuario(), "O usuário não deve ser nulo.");
	        assertEquals(usuarioDTO.getIdUsuario(), dto.getUsuario().getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals(usuarioDTO.getCpf(), dto.getUsuario().getCpf(), "O CPF do usuário deve ser 12345678900.");
	    }
	 
	 @Test
	    public void whenDefaultConstructorIsUsed_thenFieldsShouldBeInitializedWithDefaults() {
	        ContaComUsuarioDTO dto = new ContaComUsuarioDTO();

	        assertNull(dto.getIdConta(), "O ID da conta deve ser nulo.");
	        assertEquals(0, dto.getNumeroConta(), "O número da conta deve ser 0.");
	        assertNull(dto.getSaldo(), "O saldo da conta deve ser nulo.");
	        assertFalse(dto.isAtiva(), "A conta deve estar inativa.");
	        assertNull(dto.getChavePix(), "A chave Pix deve ser nula.");
	        assertNull(dto.getTipoConta(), "O tipo da conta deve ser nulo.");
	        assertNull(dto.getUsuario(), "O usuário deve ser nulo.");
	    }
	 
	 @Test
	    public void whenSettersAreUsed_thenGettersReturnCorrectValues() {
	        UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "12345678900", "Test User", "123456789", "senha");

	        ContaComUsuarioDTO dto = new ContaComUsuarioDTO();
	        dto.setIdConta(1L);
	        dto.setNumeroConta(123456);
	        dto.setSaldo(BigDecimal.valueOf(1000.00));
	        dto.setAtiva(true);
	        dto.setChavePix("chave-pix");
	        dto.setTipoConta(TipoConta.CORRENTE);
	        dto.setUsuario(usuarioDTO);

	        assertEquals(1L, dto.getIdConta(), "O ID da conta deve ser 1L.");
	        assertEquals(123456, dto.getNumeroConta(), "O número da conta deve ser 123456.");
	        assertEquals(BigDecimal.valueOf(1000.00), dto.getSaldo(), "O saldo da conta deve ser 1000.00.");
	        assertTrue(dto.isAtiva(), "A conta deve estar ativa.");
	        assertEquals("chave-pix", dto.getChavePix(), "A chave Pix deve ser 'chave-pix'.");
	        assertEquals(TipoConta.CORRENTE, dto.getTipoConta(), "O tipo da conta deve ser CHECKING.");
	        assertNotNull(dto.getUsuario(), "O usuário não deve ser nulo.");
	        assertEquals(usuarioDTO.getIdUsuario(), dto.getUsuario().getIdUsuario(), "O ID do usuário deve ser 1L.");
	        assertEquals(usuarioDTO.getCpf(), dto.getUsuario().getCpf(), "O CPF do usuário deve ser 12345678900.");
	    }

}

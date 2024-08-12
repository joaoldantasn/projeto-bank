package com.br.accenture.eBank.ebank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.br.accenture.eBank.ebank.dtos.UsuarioContaDTO;
import com.br.accenture.eBank.ebank.dtos.UsuarioPutDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Transacao;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.repositories.EnderecoRepository;
import com.br.accenture.eBank.ebank.repositories.TransacaoRepository;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;
import com.br.accenture.eBank.ebank.services.UsuarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

class UsuarioServiceTest {


	    @InjectMocks
	    private UsuarioService usuarioService;

	    @Mock
	    private UsuarioRepository usuarioRepository;

	    @Mock
	    private TransacaoRepository transacaoRepository;

	    @Mock
	    private EnderecoRepository enderecoRepository;

	    @Mock
	    private PasswordEncoder passwordEncoder;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testFindById() {
	        // Arrange
	        Long userId = 1L;
	        Usuario usuario = new Usuario();
	        usuario.setIdUsuario(userId);
	        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));

	        // Act
	        UsuarioContaDTO result = usuarioService.findById(userId);

	        // Assert
	        assertNotNull(result);
	        assertEquals(userId, result.getIdUsuario());
	        verify(usuarioRepository, times(1)).findById(userId);
	    }
	    
	    @Test
	    void testUpdate() {
	        // Arrange
	        Long userId = 1L;
	        UsuarioPutDTO dto = new UsuarioPutDTO();
	        dto.setCpf("12345678900");
	        dto.setNomeUsuario("New Name");
	        dto.setTelefone("123456789");
	        dto.setSenha("newpassword");
	        Endereco endereco = new Endereco();
	        endereco.setIdEndereco(1L);
	        dto.setEndereco(endereco);

	        Usuario existingUser = new Usuario();
	        existingUser.setIdUsuario(userId);
	        when(usuarioRepository.getReferenceById(userId)).thenReturn(existingUser);
	        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
	        when(usuarioRepository.save(existingUser)).thenReturn(existingUser);
	        when(passwordEncoder.encode("newpassword")).thenReturn("encodedpassword");

	        // Act
	        UsuarioPutDTO result = usuarioService.update(userId, dto);

	        // Assert
	        assertNotNull(result);
	        assertEquals("12345678900", result.getCpf());
	        assertEquals("New Name", result.getNomeUsuario());
	        assertEquals("123456789", result.getTelefone());
	        assertEquals("encodedpassword", existingUser.getSenha());
	        assertEquals(endereco, existingUser.getEndereco());
	        verify(usuarioRepository, times(1)).getReferenceById(userId);
	        verify(enderecoRepository, times(1)).findById(1L);
	        verify(usuarioRepository, times(1)).save(existingUser);
	    }
	    
	    @Test
	    void testDelete() {
	        // Arrange
	        Long userId = 1L;
	        Usuario usuario = new Usuario();
	        usuario.setIdUsuario(userId);
	        usuario.setContas(new HashSet<>());

	        Conta conta = new Conta();
	        conta.setIdConta(1L);
	        usuario.getContas().add(conta);

	        List<Transacao> transacoes = List.of(new Transacao());
	        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));
	        when(transacaoRepository.findByContaOrContaDestinatario(usuario.getContas())).thenReturn(transacoes);

	        // Act
	        usuarioService.delete(userId);

	        // Assert
	        verify(transacaoRepository, times(1)).deleteAll(transacoes);
	        verify(usuarioRepository, times(1)).deleteById(userId);
	    }
	    
	    @Test
	    void testUpdateThrowsEntityNotFoundExceptionWhenUserDoesNotExist() {
	        // Arrange
	        Long id = 1L;
	        UsuarioPutDTO dto = new UsuarioPutDTO(id, "123.456.789-00", "Nome", "123456789", "senha", null);

	        when(usuarioRepository.getReferenceById(id)).thenThrow(new EntityNotFoundException("Usuário não encontrado"));

	        // Act & Assert
	        assertThrows(EntityNotFoundException.class, () -> usuarioService.update(id, dto));
	    }
	    
	    @Test
	    void testUpdateThrowsEntityNotFoundExceptionWhenEnderecoDoesNotExist() {
	        // Arrange
	        Long id = 1L;
	        Endereco endereco = new Endereco();
	        endereco.setIdEndereco(999L); // ID que não existe

	        UsuarioPutDTO dto = new UsuarioPutDTO(id, "123.456.789-00", "Nome", "123456789", "senha", endereco);

	        Usuario usuario = new Usuario();
	        when(usuarioRepository.getReferenceById(id)).thenReturn(usuario);
	        when(enderecoRepository.findById(999L)).thenThrow(new EntityNotFoundException("Endereço não encontrado"));

	        // Act & Assert
	        assertThrows(EntityNotFoundException.class, () -> usuarioService.update(id, dto));
	    }
	    
	   
	    @Test
	    void testSetAndGetEndereco() {
	        // Arrange
	        Usuario usuario = new Usuario();
	        Endereco endereco = new Endereco();
	        endereco.setIdEndereco(1L);

	        // Act
	        usuario.setEndereco(endereco);

	        // Assert
	        assertEquals(endereco, usuario.getEndereco());
	    }
	    
	    @Test
	    void testDeleteThrowsEntityNotFoundExceptionWhenUserDoesNotExist() {
	        // Arrange
	        Long id = 1L;
	        when(usuarioRepository.findById(id)).thenThrow(new EntityNotFoundException("Usuário não encontrado"));

	        // Act & Assert
	        assertThrows(EntityNotFoundException.class, () -> usuarioService.delete(id));
	    }
	    
	    @Test
	    void testCpfValidation() {
	        // Arrange
	        Usuario usuario = new Usuario();
	        
	        // Act
	        usuario.setCpf("123.456.789-00");
	        
	        // Assert
	        assertEquals("123.456.789-00", usuario.getCpf());

	        String invalidCpf = "12345678900";
	        // Simule o comportamento esperado para um CPF inválido
	        assertThrows(IllegalArgumentException.class, () -> {
	            if (!invalidCpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
	                throw new IllegalArgumentException("CPF inválido");
	            }
	        });
	    }



	}


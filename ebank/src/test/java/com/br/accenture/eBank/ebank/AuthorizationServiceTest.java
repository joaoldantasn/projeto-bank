package com.br.accenture.eBank.ebank;

import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;
import com.br.accenture.eBank.ebank.services.auth.AuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorizationServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        String cpf = "12345678900";
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        usuario.setNomeUsuario("João");

        when(usuarioRepository.findByCpf(cpf)).thenReturn(usuario);

        UserDetails userDetails = authorizationService.loadUserByUsername(cpf);

        assertNotNull(userDetails);
        assertEquals(cpf, userDetails.getUsername());
        assertEquals("12345678900", userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String cpf = "12345678900";

        // Configura o mock para retornar null quando o CPF não for encontrado
        when(usuarioRepository.findByCpf(cpf)).thenReturn(null);

        // Verifica se a exceção UsernameNotFoundException é lançada
        assertThrows(
                UsernameNotFoundException.class,
                () -> authorizationService.loadUserByUsername(cpf)
        );
    }
}

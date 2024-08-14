package com.br.accenture.eBank.ebank.auth;

import com.br.accenture.eBank.ebank.configs.TokenService;
import com.br.accenture.eBank.ebank.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    void setUp() throws Exception {
        tokenService = new TokenService();
        Field field = TokenService.class.getDeclaredField("secret");
        field.setAccessible(true);
        field.set(tokenService, "test-secret");
    }

    @Test
    void testGenerateToken() {
        Usuario user = new Usuario();
        user.setCpf("12345678900");

        String token = tokenService.generateToken(user);

        assertNotNull(token);
        assertTrue(token.startsWith("eyJ"));
    }

    @Test
    void testValidateToken_ValidToken() {
        Usuario user = new Usuario();
        user.setCpf("12345678900");

        String token = tokenService.generateToken(user);
        String subject = tokenService.validateToken(token);

        assertEquals(user.getCpf(), subject);
    }

    @Test
    void testValidateToken_InvalidToken() {
        String invalidToken = "invalid.token";
        String subject = tokenService.validateToken(invalidToken);

        assertEquals("", subject);
    }

}

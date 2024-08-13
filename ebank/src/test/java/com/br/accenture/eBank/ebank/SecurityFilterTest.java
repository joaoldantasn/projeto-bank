package com.br.accenture.eBank.ebank;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.br.accenture.eBank.ebank.configs.TokenService;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioRepository userRepository;


    @Test
    void shouldRejectRequestWithMissingToken() throws Exception {
        // Perform a request without a token
        mockMvc.perform(get("/some-secured-endpoint"))
                .andExpect(status().isForbidden());

        // Verify that no interactions occur if the token is missing
        verify(tokenService, never()).validateToken(any());
        verify(userRepository, never()).findByCpf(any());
    }
}

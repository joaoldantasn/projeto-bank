package com.br.accenture.eBank.ebank.auth;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.br.accenture.eBank.ebank.configs.TokenService;
import com.br.accenture.eBank.ebank.dtos.ContaDTO;
import com.br.accenture.eBank.ebank.dtos.EnderecoDTO;
import com.br.accenture.eBank.ebank.dtos.auth.AuthenticationDTO;
import com.br.accenture.eBank.ebank.dtos.auth.RegisterDTO;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;
import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;
import com.br.accenture.eBank.ebank.repositories.AgenciaRepository;
import com.br.accenture.eBank.ebank.repositories.EnderecoRepository;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@MockBean
	private AgenciaRepository agenciaRepository;

	@MockBean
	private EnderecoRepository enderecoRepository;

	@MockBean
	private TokenService tokenService;

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@Test
	public void testLoginSuccess() throws Exception {
		AuthenticationDTO dto = new AuthenticationDTO("12345678900", "senha123");
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(new Usuario(),
				"senha123");
		when(authenticationManager.authenticate(any())).thenReturn(authToken);
		when(tokenService.generateToken(any())).thenReturn("fake-token");

		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.token").value("fake-token"));
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})  // Simula um usuário com a role USER
	public void testRegisterAccessDenied() throws Exception {
	    RegisterDTO dto = new RegisterDTO(
	        "12345678900",
	        "John Doe",
	        "123456789",
	        "senha123",
	        UserRoles.USER,  // A role do DTO deve ser USER, mas o teste simula um USER com role USER
	        1L,
	        new EnderecoDTO("12345-678", "Rua Exemplo", "Cidade Exemplo", "Bairro Exemplo", "10"),
	        Set.of(new ContaDTO(0, new BigDecimal("1000.00"), true, "chave123", TipoConta.CORRENTE))
	    );

	    mockMvc.perform(post("/auth/register")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(new ObjectMapper().writeValueAsString(dto)))
	            .andExpect(status().isForbidden());  // Espera um status 403 Forbidden
	}
	

}

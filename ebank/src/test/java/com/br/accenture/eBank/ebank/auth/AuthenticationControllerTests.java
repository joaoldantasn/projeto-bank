package com.br.accenture.eBank.ebank.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import com.br.accenture.eBank.ebank.controllers.auth.AuthenticationController;
import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.server.ResponseStatusException;

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


	@InjectMocks
	private AuthenticationController authenticationController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
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
	@Test
	void testRegisterSuccess() {
		EnderecoDTO enderecoDTO = new EnderecoDTO("cep", "logradouro", "cidade", "bairro", "numero");
		ContaDTO contaDTO = new ContaDTO(12345,BigDecimal.valueOf(100), true, "chavePix", TipoConta.POUPANCA);
		RegisterDTO registerDTO = new RegisterDTO(
				"12345678900", "password", "nomeUsuario", "telefone", UserRoles.USER,1L, enderecoDTO, Set.of(contaDTO));


		when(usuarioRepository.findByCpf(anyString())).thenReturn(null);
		when(agenciaRepository.findById(anyLong())).thenReturn(Optional.of(new Agencia()));
		when(enderecoRepository.save(any(Endereco.class))).thenReturn(new Endereco());
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

		ResponseEntity<String> response = authenticationController.register(registerDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Usuário registrado com sucesso.", response.getBody());
	}

	@Test
	void testRegisterUserAlreadyExists() {

		EnderecoDTO enderecoDTO = new EnderecoDTO("cep", "logradouro", "cidade", "bairro", "numero");
		ContaDTO contaDTO = new ContaDTO(12345,BigDecimal.valueOf(100), true, "chavePix", TipoConta.POUPANCA);
		RegisterDTO registerDTO = new RegisterDTO(
				"12345678900", "password", "nomeUsuario", "telefone", UserRoles.USER,1L, enderecoDTO, Set.of(contaDTO));


		when(usuarioRepository.findByCpf(anyString())).thenReturn(new Usuario());

		ResponseEntity<String> response = authenticationController.register(registerDTO);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Usuário já existente com o CPF fornecido.", response.getBody());
	}

	@Test
	void testRegisterAgenciaNotFound() {

		EnderecoDTO enderecoDTO = new EnderecoDTO("cep", "logradouro", "cidade", "bairro", "numero");
		ContaDTO contaDTO = new ContaDTO(12345,BigDecimal.valueOf(100), true, "chavePix", TipoConta.POUPANCA);
		RegisterDTO registerDTO = new RegisterDTO(
				"12345678900", "password", "nomeUsuario", "telefone", UserRoles.USER,1L, enderecoDTO, Set.of(contaDTO));

		when(usuarioRepository.findByCpf(anyString())).thenReturn(null);
		when(agenciaRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(ResponseStatusException.class, () -> authenticationController.register(registerDTO));
	}

}

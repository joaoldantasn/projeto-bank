package com.br.accenture.eBank.ebank.conta;

import com.br.accenture.eBank.ebank.controllers.ContaController;
import com.br.accenture.eBank.ebank.dtos.conta.ContaResponseDTO;
import com.br.accenture.eBank.ebank.dtos.transacao.ExtratoDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;
import com.br.accenture.eBank.ebank.services.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
public class ContaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContaService service;

    @InjectMocks
    private ContaController contaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contaController).build();
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void testGetById() throws Exception {
        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setIdConta(1L);

        when(service.findById(1L)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/conta/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idConta").value(1));
    }

    @Test
    void testGetByChavePix() throws Exception {
        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setIdConta(1L);

        when(service.findByChavePix("chave-pix")).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/conta/pix")
                        .param("chave", "chave-pix")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idConta").value(1));
    }

    @Test
    void testInsert() throws Exception {
        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setIdConta(1L);

        when(service.insert(any(ContaResponseDTO.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idConta").value(1));
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void testUpdate() throws Exception {
        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setIdConta(1L);

        when(service.update(eq(1L), any(ContaResponseDTO.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/conta/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idConta").value(1));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/conta/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    void testGetExtrato() throws Exception {
        ExtratoDTO extratoDTO = new ExtratoDTO();

        when(service.getExtrato(eq(1L), any(Instant.class), any(Instant.class))).thenReturn(extratoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/conta/extrato/{id}", 1)
                        .param("startDate", Instant.now().toString())
                        .param("endDate", Instant.now().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

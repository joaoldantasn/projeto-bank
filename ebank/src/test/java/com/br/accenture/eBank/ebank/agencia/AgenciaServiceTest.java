package com.br.accenture.eBank.ebank.agencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.br.accenture.eBank.ebank.dtos.AgenciaComUsuariosDTO;
import com.br.accenture.eBank.ebank.dtos.AgenciaDTO;
import com.br.accenture.eBank.ebank.dtos.UsuarioEContaDTO;
import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;
import com.br.accenture.eBank.ebank.repositories.AgenciaRepository;
import com.br.accenture.eBank.ebank.services.AgenciaService;

class AgenciaServiceTest {

    @InjectMocks
    private AgenciaService service;

    @Mock
    private AgenciaRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;

        // Criando e configurando a entidade Agencia
        Endereco endereco = new Endereco(1L, "Rua A", "12345678", "Cidade", "Estado", "123");
        Agencia agencia = new Agencia(id, 1001, "999999999", endereco);

        // Criando e configurando a entidade Usuario sem a senha
        Conta conta = new Conta(1L, 12345, BigDecimal.ZERO, true, "chavePix", TipoConta.CORRENTE);
        Set<Conta> contas = Set.of(conta);
        Usuario usuario = new Usuario(1L, "123.456.789-01", "Usuario 1", contas);
        usuario.setAgencia(agencia);

        conta.setUsuario(usuario);  // Associando o usuário à conta

        Set<Usuario> usuarios = new HashSet<>();
        usuarios.add(usuario);

        agencia.setUsuarios(usuarios);

        when(repository.findById(id)).thenReturn(Optional.of(agencia));

        AgenciaComUsuariosDTO dto = service.findById(id);

        assertNotNull(dto);
        assertEquals(id, dto.getIdAgencia());
        assertEquals(1001, dto.getCodAgencia());
        assertEquals("999999999", dto.getTelefone());
        assertEquals(endereco, dto.getEndereco());
        assertFalse(dto.getUsuarios().isEmpty());
        assertEquals(1, dto.getUsuarios().size());
        UsuarioEContaDTO usuarioDTO = dto.getUsuarios().iterator().next();
        assertEquals("123.456.789-01", usuarioDTO.getCpf());
        assertEquals("Usuario 1", usuarioDTO.getNomeUsuario());
        verify(repository, times(1)).findById(id);
    }
    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);

        // Criando e configurando uma lista de entidades Agencia
        Endereco endereco = new Endereco(1L, "Rua A", "12345678", "Cidade", "Estado", "123");
        Agencia agencia = new Agencia(1L, 1001, "999999999", endereco);

        Page<Agencia> page = new PageImpl<>(List.of(agencia));
        when(repository.findAll(pageable)).thenReturn(page);

        Page<AgenciaDTO> result = service.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(agencia.getIdAgencia(), result.getContent().get(0).getIdAgencia());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void testInsert() {
        // Criando e configurando o DTO AgenciaDTO
        Endereco endereco = new Endereco(1L, "Rua A", "12345678", "Cidade", "Estado", "123");
        AgenciaDTO dto = new AgenciaDTO(1L, 1001, endereco, "999999999");

        Agencia agencia = new Agencia(1L, 1001, "999999999", endereco);
        when(repository.save(any(Agencia.class))).thenReturn(agencia);

        AgenciaDTO result = service.insert(dto);

        assertNotNull(result);
        assertEquals(agencia.getIdAgencia(), result.getIdAgencia());
        verify(repository, times(1)).save(any(Agencia.class));
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository, times(1)).deleteById(id);
    }
    
    @Test
    void testFindAllThrowsExceptionWhenRepositoryFails() {
        Pageable pageable = PageRequest.of(0, 10);

        when(repository.findAll(pageable)).thenThrow(new RuntimeException("Erro no repositório"));

        assertThrows(RuntimeException.class, () -> service.findAll(pageable));
    }
    
    @Test
    void testInsertThrowsExceptionWhenSavingFails() {
        Endereco endereco = new Endereco(1L, "Rua A", "12345678", "Cidade", "Estado", "123");
        AgenciaDTO dto = new AgenciaDTO(null, 1001, endereco, "999999999");

        when(repository.save(any(Agencia.class))).thenThrow(new RuntimeException("Falha ao salvar"));

        assertThrows(RuntimeException.class, () -> service.insert(dto));
    }
    
    @Test
    void testDeleteThrowsExceptionWhenDeleteFails() {
        Long id = 1L;

        doThrow(new RuntimeException("Falha ao deletar")).when(repository).deleteById(id);

        assertThrows(RuntimeException.class, () -> service.delete(id));
    }
    
    @Test
    void testFindByIdWithNullValues() {
        Long id = 1L;

        // Criando uma agência com valores válidos
        Endereco endereco = new Endereco(1L, "Rua A", "12345678", "Cidade", "Estado", "123");
        Agencia agencia = new Agencia(id, 1001, "999999999", endereco);
        agencia.setUsuarios(new HashSet<>());  // Garantindo que a lista de usuários não é nula

        when(repository.findById(id)).thenReturn(Optional.of(agencia));

        AgenciaComUsuariosDTO dto = service.findById(id);

        assertNotNull(dto);
        assertEquals(id, dto.getIdAgencia());
        assertEquals(1001, dto.getCodAgencia());
        assertEquals("999999999", dto.getTelefone());
        assertEquals(endereco, dto.getEndereco());
        assertTrue(dto.getUsuarios().isEmpty());
    }

    
}
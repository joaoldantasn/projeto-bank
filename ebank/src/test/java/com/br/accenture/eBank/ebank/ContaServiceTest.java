package com.br.accenture.eBank.ebank;

import com.br.accenture.eBank.ebank.dtos.ContaResponseDTO;
import com.br.accenture.eBank.ebank.dtos.ExtratoDTO;
import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.TipoConta;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.repositories.AgenciaRepository;
import com.br.accenture.eBank.ebank.repositories.ContaRepository;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;
import com.br.accenture.eBank.ebank.services.ContaService;
import com.br.accenture.eBank.ebank.services.TransacaoService;
import com.br.accenture.eBank.ebank.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContaServiceTest {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContaService contaService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AgenciaRepository agenciaRepository;

    @BeforeEach
    public void setUp() {

    }


    @Test
    public void testFindAll() {
        Conta conta1 = new Conta();
        conta1.setChavePix("chave1@pix.com");
        conta1.setSaldo(BigDecimal.valueOf(100.00));
        contaRepository.save(conta1);

        Conta conta2 = new Conta();
        conta2.setChavePix("chave2@pix.com");
        conta2.setSaldo(BigDecimal.valueOf(200.00));
        contaRepository.save(conta2);

        Pageable pageable = PageRequest.of(0, 10);
        Page<ContaResponseDTO> result = contaService.findAll(pageable);
        assertEquals(13, result.getTotalElements());

    }
    @Test
    public void testFindById_Success() {
        Conta conta = new Conta();
        conta.setTipoConta(TipoConta.CORRENTE);
        conta.setNumeroConta(12345);
        conta.setChavePix("chave-pix");
        conta.setSaldo(BigDecimal.valueOf(1000.00));
        conta = contaRepository.save(conta);

        ContaResponseDTO dto = contaService.findById(conta.getIdConta());

        assertNotNull(dto);
        assertEquals(conta.getIdConta(), dto.getIdConta());
        assertEquals(conta.getNumeroConta(), dto.getNumeroConta());
    }

    @Test
    public void testFindById_NotFound() {
        assertThrows(ContaNaoEncontradaException.class, () -> contaService.findById(999L));
    }

    @Test
    public void testInsert_Success() {
        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setTipoConta(TipoConta.POUPANCA);
        dto.setNumeroConta(54321);
        dto.setChavePix("outra-chave-pix");
        dto.setSaldo(BigDecimal.valueOf(500.00));

        ContaResponseDTO result = contaService.insert(dto);

        assertNotNull(result);
        assertEquals(dto.getNumeroConta(), result.getNumeroConta());
    }

    @Test
    public void testUpdate_Success() {
        Conta conta = new Conta();
        conta.setTipoConta(TipoConta.CORRENTE);
        conta.setNumeroConta(12345);
        conta.setChavePix("chave-pix");
        conta.setSaldo(BigDecimal.valueOf(1000.00));
        conta = contaRepository.save(conta);

        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setTipoConta(TipoConta.POUPANCA);
        dto.setNumeroConta(12345);
        dto.setChavePix("chave-pix");
        dto.setSaldo(BigDecimal.valueOf(2000.00));

        ContaResponseDTO result = contaService.update(conta.getIdConta(), dto);

        assertNotNull(result);
        assertEquals(dto.getTipoConta(), result.getTipoConta());
        assertEquals(dto.getSaldo(), result.getSaldo());
    }

    @Test
    public void testDelete_Success() {
        Conta conta = new Conta();
        conta.setTipoConta(TipoConta.POUPANCA);
        conta.setNumeroConta(12345);
        conta.setChavePix("chave-pix");
        conta.setSaldo(BigDecimal.valueOf(1000.00));
        conta = contaRepository.save(conta);

        contaService.delete(conta.getIdConta());

        assertFalse(contaRepository.existsById(conta.getIdConta()));
    }

    @Test
    public void testFindByChavePix() {

        Conta conta = new Conta();
        conta.setChavePix("pix@pix.com");
        contaRepository.save(conta);
        ContaResponseDTO contaSaved = contaService.findByChavePix("pix@pix.com");
        assertEquals("pix@pix.com", contaSaved.getChavePix());
    }

    @Test
    public void testFindByChavePixNotFound() {
        assertThrows(ContaNaoEncontradaException.class, () ->
                contaService.findByChavePix("chave@pix.com"));
    }

    @Test
    public void testGetExtrato_Success() {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("João");
        usuario.setCpf("123.456.789-00");
        usuario = usuarioRepository.save(usuario);

        Endereco endereco = new Endereco();
        endereco.setIdEndereco(1L);
        endereco.setCep("54545-000");
        endereco.setBairro("Bela Vista");
        endereco.setCidade("Monteiro");
        endereco.setNumero("45");

        Agencia agencia = new Agencia(1L, 12345, "83123456789", endereco);
        usuario.setAgencia(agencia);
        Conta conta = new Conta();
        conta.setTipoConta(TipoConta.POUPANCA);
        conta.setNumeroConta(12345);
        conta.setChavePix("chave-pix");
        conta.setSaldo(BigDecimal.valueOf(1000.00));
        conta.setUsuario(usuario); // Associar o usuário

        usuarioRepository.save(usuario);
        conta = contaRepository.save(conta);

        Instant startDate = Instant.now().minusSeconds(3600);
        Instant endDate = Instant.now();


        ExtratoDTO extrato = contaService.getExtrato(conta.getIdConta(), startDate, endDate);

        assertNotNull(extrato);
        assertEquals(conta.getNumeroConta(), extrato.getNumConta());
        assertEquals(usuario.getNomeUsuario(), extrato.getNomeUsuario());
    }

    @Test
    public void testGetExtrato_NotFound() {

        Instant startDate = Instant.now().minusSeconds(3600);
        Instant endDate = Instant.now();

        assertThrows(ContaNaoEncontradaException.class, () ->
                contaService.getExtrato(99L, startDate, endDate));



    }
}

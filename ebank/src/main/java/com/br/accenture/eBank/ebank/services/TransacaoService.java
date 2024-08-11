package com.br.accenture.eBank.ebank.services;

import com.br.accenture.eBank.ebank.dtos.ContaResponseDTO;
import com.br.accenture.eBank.ebank.dtos.TransacaoExtratoDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Transacao;
import com.br.accenture.eBank.ebank.entities.enums.Operacao;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.exceptions.SaldoInsuficienteException;
import com.br.accenture.eBank.ebank.repositories.ContaRepository;
import com.br.accenture.eBank.ebank.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    public void sacar(Long contaId, BigDecimal valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException(valor);
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setDataHora(Instant.now());
        transacao.setValor(valor.negate());
        transacao.setTipo(Operacao.SAQUE);

        transacaoRepository.save(transacao);
    }

    public void depositar(Long contaId, BigDecimal valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));

        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setDataHora(Instant.now());
        transacao.setValor(valor);
        transacao.setTipo(Operacao.DEPOSITO);

        transacaoRepository.save(transacao);
    }

    public void transferir(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
        Conta contaOrigem = contaRepository.findById(contaOrigemId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de origem não encontrada"));
        Conta contaDestino = contaRepository.findById(contaDestinoId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de destino não encontrada"));

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException(valor);
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        Transacao transacao = new Transacao();
        transacao.setConta(contaOrigem);
        transacao.setContaDestinatario(contaDestino);
        transacao.setDataHora(Instant.now());
        transacao.setValor(valor.negate());
        transacao.setTipo(Operacao.TRANSFERENCIA);

        transacaoRepository.save(transacao);
    }

    public void transferirViaPix(Long contaOrigemId, String chavePix, BigDecimal valor) {
        System.out.println(chavePix);
        Conta contaOrigem = contaRepository.findById(contaOrigemId)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de origem não encontrada"));
        Conta contaDestino = contaRepository.findContaByChavePix(chavePix)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta de destino não encontrada"));

        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException(valor);
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        Transacao transacao = new Transacao();
        transacao.setConta(contaOrigem);
        transacao.setContaDestinatario(contaDestino);
        transacao.setDataHora(Instant.now());
        transacao.setValor(valor.negate());
        transacao.setTipo(Operacao.TRANSFERENCIA);

        transacaoRepository.save(transacao);
    }

    public List<TransacaoExtratoDTO> buscarTransacoesPorPeriodo(Conta conta, Instant startDate, Instant endDate) {
        return copyEntitiesToDtos(transacaoRepository.findByContaAndDataHoraBetween(conta, startDate, endDate));
    }

    public List<TransacaoExtratoDTO> copyEntitiesToDtos(List<Transacao> entities) {
        return entities.stream().map(transacao -> {
            TransacaoExtratoDTO dto = new TransacaoExtratoDTO();

            dto.setId(transacao.getIdTransacao());
            dto.setDataHora(transacao.getDataHora());
            dto.setTipo(transacao.getTipo());
            dto.setValor(transacao.getValor());
            if (transacao.getContaDestinatario() != null) {
                dto.setContaDestinatario(new ContaResponseDTO(transacao.getContaDestinatario()));
            }

            return dto;
        }).collect(Collectors.toList());
    }
}

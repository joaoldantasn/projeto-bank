package com.br.accenture.eBank.ebank.services;

import com.br.accenture.eBank.ebank.dtos.conta.ContaResponseDTO;
import com.br.accenture.eBank.ebank.dtos.transacao.ExtratoDTO;
import com.br.accenture.eBank.ebank.dtos.transacao.TransacaoExtratoDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private TransacaoService transacaoService;


    @Transactional
    public ContaResponseDTO findById(Long id) {
        Conta resultado = repository.findById(id).orElseThrow(()->  new ContaNaoEncontradaException("Conta não encontrada"));
        return new ContaResponseDTO(resultado);
    }

    @Transactional
    public ContaResponseDTO findByChavePix(String chave) {
        Conta resultado = repository.findContaByChavePix(chave).orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
        return new ContaResponseDTO(resultado);
    }

    @Transactional(readOnly = true)
    public Page<ContaResponseDTO> findAll(Pageable pageable) {
        Page<Conta> resultado = repository.findAll(pageable);
        return resultado.map(ContaResponseDTO::new);
    }


    @Transactional
    public ContaResponseDTO insert(ContaResponseDTO dto) {
        Conta entity = new Conta();
        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ContaResponseDTO(entity);
    }

    @Transactional
    public ContaResponseDTO update(Long id, ContaResponseDTO dto) {
        Conta entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ContaResponseDTO(entity);
    }

    @Transactional
    public void copyDtoToEntity(ContaResponseDTO dto, Conta entity) {
        entity.setTipoConta(dto.getTipoConta());
        entity.setNumeroConta(dto.getNumeroConta());
        entity.setChavePix(dto.getChavePix());
        entity.setSaldo(dto.getSaldo());
    }


    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public ExtratoDTO getExtrato(Long contaId, Instant startDate, Instant endDate) {

        Conta conta = repository.findById(contaId).orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada!"));

        List<TransacaoExtratoDTO> transacoes = transacaoService.buscarTransacoesPorPeriodo(conta, startDate, endDate);

        ExtratoDTO extrato = new ExtratoDTO(new Date().toInstant(), conta.getUsuario().getNomeUsuario(), "Extrato",
                conta.getUsuario().getAgencia().getCodAgencia(), conta.getNumeroConta(), transacoes, startDate, endDate);

        return extrato;
    }
}

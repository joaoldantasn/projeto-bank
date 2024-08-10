package com.br.accenture.eBank.ebank.services;

import com.br.accenture.eBank.ebank.dtos.*;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.exceptions.ContaNaoEncontradaException;
import com.br.accenture.eBank.ebank.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ContaDTO findById(Long id) {
        Conta resultado = repository.findById(id).orElseThrow(()->  new ContaNaoEncontradaException("Conta não encontrada"));
        return new ContaDTO(resultado);
    }

    @Transactional(readOnly = true)
    public Page<ContaDTO> findAll(Pageable pageable) {
        Page<Conta> resultado = repository.findAll(pageable);
        return resultado.map(ContaDTO::new);
    }


    @Transactional
    public ContaDTO insert(ContaDTO dto) {
        Conta entity = new Conta();
        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ContaDTO(entity);
    }

    @Transactional
    public ContaDTO update(Long id, ContaDTO dto) {
        Conta entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ContaDTO(entity);
    }

    @Transactional
    public void copyDtoToEntity(ContaDTO dto, Conta entity) {
        entity.setTipoConta(dto.getTipoConta());
        entity.setNumeroConta(dto.getNumeroConta());
        entity.setChavePix(dto.getChavePix());
        entity.setSaldo(dto.getSaldo());
    }


    @Transactional
    public ContaDTO copyEntityToDto(Conta entity) {

        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setIdConta(entity.getIdConta());
        contaDTO.setTipoConta(entity.getTipoConta());
        contaDTO.setNumeroConta(entity.getNumeroConta());
        contaDTO.setTipoConta(entity.getTipoConta());
        contaDTO.setChavePix(entity.getChavePix());
        contaDTO.setSaldo(entity.getSaldo());

        return contaDTO;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public ExtratoDTO getExtrato(Long contaId, Instant startDate, Instant endDate) {

        Conta conta = repository.findById(contaId).orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada!"));

        List<TransacaoExtratoDTO> transacoes = transacaoService.buscarTransacoesPorPeriodo(conta, startDate, endDate);

        ExtratoDTO extrato = new ExtratoDTO();
        extrato.setNomeUsuario(conta.getUsuario().getNomeUsuario());
        extrato.setNumAgencia(conta.getUsuario().getAgencia().getCodAgencia());
        extrato.setNumConta(conta.getNumeroConta());
        extrato.setTransacoes(transacoes);
        extrato.setDataHoraGeracao(new Date().toInstant());
        extrato.setPeriodoInicio(startDate);
        extrato.setPeriodoFim(endDate);
        extrato.setDescricao("Extrato");

        return extrato;
    }



}

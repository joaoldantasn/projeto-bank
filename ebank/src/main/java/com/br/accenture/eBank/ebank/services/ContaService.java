package com.br.accenture.eBank.ebank.services;

import com.br.accenture.eBank.ebank.dtos.ContaDTO;
import com.br.accenture.eBank.ebank.dtos.ExtratoDTO;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Extrato;
import com.br.accenture.eBank.ebank.repositories.ContaRepository;
import com.br.accenture.eBank.ebank.repositories.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ExtratoRepository extratoRepository;


    @Transactional
    public ContaDTO findById(Long id) {
        Conta resultado = repository.findById(id).orElseThrow(()->  new NullPointerException("Conta não encontrada"));
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
    private void copyDtoToEntity(ContaDTO dto, Conta entity) {
        entity.setTipoConta(dto.getTipoConta());
        entity.setNumeroConta(dto.getNumeroConta());
        entity.setTipoConta(dto.getTipoConta());
        entity.setChavePix(dto.getChavePix());
        entity.setSaldo(dto.getSaldo());
        entity.setUsuario(dto.getUsuario());
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ExtratoDTO getExtrato(Long contaId) {

        Extrato extrato = extratoRepository.findByConta_IdConta(contaId).orElseThrow(() -> new RuntimeException("Conta Nào encontrada!"));
        System.out.println(extrato.toString());
        return new ExtratoDTO(extrato);
    }


}

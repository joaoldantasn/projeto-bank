package com.br.accenture.eBank.ebank.controllers;

import com.br.accenture.eBank.ebank.dtos.ContaDTO;
import com.br.accenture.eBank.ebank.dtos.ExtratoDTO;
import com.br.accenture.eBank.ebank.repositories.ExtratoRepository;
import com.br.accenture.eBank.ebank.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    private ContaService service;


    @GetMapping
    public ResponseEntity<Page<ContaDTO>> findAll(Pageable pageable) {
        Page<ContaDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> getById(@PathVariable Long id) {
        ContaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ContaDTO> insert(@RequestBody ContaDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getIdConta()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ContaDTO> update(@PathVariable Long id, @RequestBody ContaDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/extrato/{id}")
    public ResponseEntity<ExtratoDTO> getExtrato(@PathVariable Long id) {
        return ResponseEntity.ok(service.getExtrato(id));
    }

}
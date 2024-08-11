package com.br.accenture.eBank.ebank.controllers;

import com.br.accenture.eBank.ebank.dtos.ContaResponseDTO;
import com.br.accenture.eBank.ebank.dtos.ExtratoDTO;
import com.br.accenture.eBank.ebank.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@RestController
@RequestMapping(value = "/api/conta")
public class ContaController {

    @Autowired
    private ContaService service;


    @GetMapping
    public ResponseEntity<Page<ContaResponseDTO>> findAll(Pageable pageable) {
        Page<ContaResponseDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id == principal.idUsuario")
    public ResponseEntity<ContaResponseDTO> getById(@PathVariable Long id) {
        ContaResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pix")
    public ResponseEntity<ContaResponseDTO> getByChavePix(@RequestParam String chave) {
        ContaResponseDTO dto = service.findByChavePix(chave);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ContaResponseDTO> insert(@RequestBody ContaResponseDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("#id == principal.idUsuario")
    public ResponseEntity<ContaResponseDTO> update(@PathVariable Long id, @RequestBody ContaResponseDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/extrato/{id}")
    @PreAuthorize("#id == principal.idUsuario")
    public ResponseEntity<ExtratoDTO> getExtrato(
            @PathVariable Long id,
            @RequestParam("startDate") Instant startDate,
            @RequestParam("endDate") Instant endDate) {

        ExtratoDTO extrato = service.getExtrato(id, startDate, endDate);

        return ResponseEntity.ok(extrato);
    }
}
package com.br.accenture.eBank.ebank.controllers;

import java.math.BigDecimal;

import com.br.accenture.eBank.ebank.dtos.transacao.TransacaoDTO;
import com.br.accenture.eBank.ebank.entities.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.accenture.eBank.ebank.services.TransacaoService;


@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @PostMapping("/sacar")
    public ResponseEntity<?> sacar(@RequestParam("contaId") Long contaId,
                                        @RequestParam("valor") BigDecimal valor) {
        try {
            TransacaoDTO transacao =  transacaoService.sacar(contaId, valor);
            return ResponseEntity.ok(transacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar saque: " + e.getMessage());
        }
    }

    @PostMapping("/depositar")
    public ResponseEntity<?> depositar(@RequestParam("contaId") Long contaId,
                                            @RequestParam("valor") BigDecimal valor) {
        try {
            TransacaoDTO transacao = transacaoService.depositar(contaId, valor);
            return ResponseEntity.ok(transacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestParam("contaOrigemId") Long contaOrigemId,
                                                   @RequestParam("numeroConta") int numeroConta,
                                                   @RequestParam("valor") BigDecimal valor) {
        try {
           TransacaoDTO transacao = transacaoService.transferir(contaOrigemId, numeroConta, valor);
            return ResponseEntity.ok(transacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar transferência: " + e.getMessage());
        }
    }

    @PostMapping("/transferir/pix")
    public ResponseEntity<?> transferirViaPix(@RequestParam("contaOrigemId") Long contaOrigemId,
                                             @RequestParam("chavePix") String chavePix,
                                             @RequestParam("valor") BigDecimal valor) {
        try {
            TransacaoDTO transacao = transacaoService.transferirViaPix(contaOrigemId, chavePix, valor);
            return ResponseEntity.ok(transacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar transferência: " + e.getMessage());
        }
    }

}

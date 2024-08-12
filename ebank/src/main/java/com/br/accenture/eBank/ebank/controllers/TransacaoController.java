package com.br.accenture.eBank.ebank.controllers;

import java.math.BigDecimal;

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
    public ResponseEntity<String> sacar(@RequestParam("contaId") Long contaId,
                                        @RequestParam("valor") BigDecimal valor) {
        try {
            transacaoService.sacar(contaId, valor);
            return ResponseEntity.ok("Saque realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar saque: " + e.getMessage());
        }
    }

    @PostMapping("/depositar")
    public ResponseEntity<String> depositar(@RequestParam("contaId") Long contaId,
                                            @RequestParam("valor") BigDecimal valor) {
        try {
            transacaoService.depositar(contaId, valor);
            return ResponseEntity.ok("Depósito realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestParam("contaOrigemId") Long contaOrigemId,
                                             @RequestParam("contaDestinoId") Long contaDestinoId,
                                             @RequestParam("valor") BigDecimal valor) {
        try {
            transacaoService.transferir(contaOrigemId, contaDestinoId, valor);
            return ResponseEntity.ok("Transferência realizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar transferência: " + e.getMessage());
        }
    }

    @PostMapping("/transferir/pix")
    public ResponseEntity<String> transferirViaPix(@RequestParam("contaOrigemId") Long contaOrigemId,
                                             @RequestParam("chavePix") String chavePix,
                                             @RequestParam("valor") BigDecimal valor) {
        try {
            transacaoService.transferirViaPix(contaOrigemId, chavePix, valor);
            return ResponseEntity.ok("Transferência realizada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao realizar transferência: " + e.getMessage());
        }
    }

}

package com.br.accenture.eBank.ebank.controllers.auth;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.accenture.eBank.ebank.configs.TokenService;
import com.br.accenture.eBank.ebank.dtos.auth.AuthenticationDTO;
import com.br.accenture.eBank.ebank.dtos.auth.LoginResponseDTO;
import com.br.accenture.eBank.ebank.dtos.auth.RegisterDTO;
import com.br.accenture.eBank.ebank.entities.Agencia;
import com.br.accenture.eBank.ebank.entities.Conta;
import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.repositories.AgenciaRepository;
import com.br.accenture.eBank.ebank.repositories.EnderecoRepository;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AgenciaRepository agenciaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
        // Verifica se já existe um usuário com o CPF fornecido
        if (this.repository.findByCpf(data.cpf()) != null) {
            return ResponseEntity.badRequest().body("Usuário já existente com o CPF fornecido.");
        }

        // Encripta a senha fornecida
        String encryptedPassword = passwordEncoder.encode(data.senha());

        // Busca a agência pelo ID
        Agencia agencia = agenciaRepository.findById(data.idAgencia())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agência não encontrada"));

        // Converte o EnderecoDTO para Endereco
        Endereco endereco = new Endereco(
            null,  // ID será gerado automaticamente
            data.endereco().cep(),
            data.endereco().logradouro(),
            data.endereco().cidade(),
            data.endereco().bairro(),
            data.endereco().numero()
        );

        // Salva o Endereco
        endereco = enderecoRepository.save(endereco);

        // Cria o novo usuário
        Usuario newUser = new Usuario(
            null,  // ID será gerado automaticamente
            data.cpf(),
            encryptedPassword,
            data.nomeUsuario(),
            data.telefone(),
            data.role(),
            agencia,
            endereco,
            new HashSet<>() // Inicializa com um conjunto vazio para as contas
        );

        // Cria e associa as contas ao usuário
        Set<Conta> contas = data.contas().stream()
                .map(contaDTO -> {
                    Conta conta = new Conta(
                            null,  // ID será gerado automaticamente
                            generateNumeroConta(),  // Gera o número da conta automaticamente
                            contaDTO.saldo(),
                            contaDTO.ativa(),
                            contaDTO.chavePix(),
                            contaDTO.tipoConta()
                    );
                    conta.setUsuario(newUser);  // Associa a conta ao usuário
                    return conta;
                })
                .collect(Collectors.toSet());

        // Adiciona as contas ao usuário
        newUser.setContas(contas);

        // Salva o novo usuário
        try {
            this.repository.save(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o usuário: " + e.getMessage());
        }

        return ResponseEntity.ok().body("Usuário registrado com sucesso.");
    }

    // Método para gerar número de conta aleatório
    private int generateNumeroConta() {
        Random random = new Random();
        return random.nextInt(90000000) + 10000000; // Exemplo de 8 dígitos
    }

}

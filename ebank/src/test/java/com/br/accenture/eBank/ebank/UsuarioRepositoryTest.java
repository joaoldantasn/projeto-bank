package com.br.accenture.eBank.ebank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;
import com.br.accenture.eBank.ebank.repositories.UsuarioRepository;

@DataJpaTest
@Transactional
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
     
    }

    @Test
    public void testFindByCpf_Success() {
        

        String cpf = "123.456.789-00";
        UserDetails userDetails = usuarioRepository.findByCpf(cpf);

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(cpf);
        assertThat(userDetails.getPassword()).isEqualTo("$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq");
        assertThat(userDetails.getAuthorities()).isNotEmpty();
    }

    @Test
    public void testFindByCpf_NotFound() {
        String cpf = "000.000.000-00";
        UserDetails userDetails = usuarioRepository.findByCpf(cpf);

        assertThat(userDetails).isNull();
    }

    @Test
    public void testSave_ValidUser() {
        Endereco endereco = new Endereco();
        endereco.setCep("01234-567");
        endereco.setLogradouro("Rua Teste 3");
        endereco.setCidade("Cidade Teste 3");
        endereco.setBairro("Bairro Teste 3");
        endereco.setNumero("707");

        Usuario newUsuario = new Usuario();
        newUsuario.setCpf("111.222.333-44");
        newUsuario.setNomeUsuario("Teste Novo");
        newUsuario.setTelefone("1234567890");
        newUsuario.setSenha("$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq");
        newUsuario.setRole(UserRoles.USER);
        newUsuario.setEndereco(endereco);

        Usuario savedUsuario = usuarioRepository.save(newUsuario);

        assertThat(savedUsuario).isNotNull();
        assertThat(savedUsuario.getIdUsuario()).isNotNull();
        assertThat(savedUsuario.getCpf()).isEqualTo("111.222.333-44");
        assertThat(savedUsuario.getNomeUsuario()).isEqualTo("Teste Novo");
    }

    @Test
    public void testSave_InvalidUser() {
        Usuario invalidUsuario = new Usuario();
        invalidUsuario.setCpf(null); 

        try {
            usuarioRepository.save(invalidUsuario);
            assertThat(true).isFalse();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(jakarta.validation.ConstraintViolationException.class);
        }
    }
    
    @Test
    public void testDeleteByCpf_Success() {
        
        Usuario usuario = new Usuario();
        usuario.setCpf("111.222.333-44");
        usuario.setNomeUsuario("Teste Remover");
        usuario.setTelefone("1234567890");
        usuario.setSenha("$2a$12$N4g0VLAUu75chvfKd5mhuuzdmJhW85HZ3holFKguUiYJvfirwYHhq");
        usuario.setRole(UserRoles.USER);
        usuario.setEndereco(new Endereco());

        Usuario savedUsuario = usuarioRepository.save(usuario);
        Long usuarioId = savedUsuario.getIdUsuario();

       
        Usuario foundUsuario = usuarioRepository.findById(usuarioId).orElse(null);
        assertThat(foundUsuario).isNotNull();

        
        usuarioRepository.deleteById(usuarioId);

      
        Usuario deletedUsuario = usuarioRepository.findById(usuarioId).orElse(null);
        assertThat(deletedUsuario).isNull();
    }


}
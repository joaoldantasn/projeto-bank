package com.br.accenture.eBank.ebank.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.br.accenture.eBank.ebank.entities.Endereco;
import com.br.accenture.eBank.ebank.entities.Usuario;
import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;

class UsuarioTest {
	
	

    // Testar o construtor com parâmetros limitados
    @Test
    void testConstructorWithLimitedParameters() {
        // Arrange
        Long idUsuario = 1L;
        String cpf = "123.456.789-00";
        String nomeUsuario = "João Silva";	
        String telefone = "1234445678";
        String senha = "senhaCriptografada";
        Endereco endereco = new Endereco();  // Você pode precisar configurar o objeto Endereco

        // Act
        Usuario usuario = new Usuario(idUsuario, cpf, nomeUsuario, telefone, senha, endereco);

        // Assert
        assertEquals(idUsuario, usuario.getIdUsuario());
        assertEquals(cpf, usuario.getCpf());
        assertEquals(nomeUsuario, usuario.getNomeUsuario());
        assertEquals(telefone, usuario.getTelefone());
        assertEquals(senha, usuario.getSenha());
        assertEquals(endereco, usuario.getEndereco());
    }

    // Testar isAccountNonExpired
    @Test
    void testIsAccountNonExpired() {
        // Arrange
        Usuario usuario = new Usuario();

        // Act
        boolean result = usuario.isAccountNonExpired();

        // Assert
        assertTrue(result);
    }

    // Testar isAccountNonLocked
    @Test
    void testIsAccountNonLocked() {
        // Arrange
        Usuario usuario = new Usuario();

        // Act
        boolean result = usuario.isAccountNonLocked();

        // Assert
        assertTrue(result);
    }

    // Testar isCredentialsNonExpired
    @Test
    void testIsCredentialsNonExpired() {
        // Arrange
        Usuario usuario = new Usuario();

        // Act
        boolean result = usuario.isCredentialsNonExpired();

        // Assert
        assertTrue(result);
    }

    // Testar isEnabled
    @Test
    void testIsEnabled() {
        // Arrange
        Usuario usuario = new Usuario();

        // Act
        boolean result = usuario.isEnabled();

        // Assert
        assertTrue(result);
    }

    @Test
    void testGetAuthoritiesForAdmin() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setRole(UserRoles.ADMIN);

        // Act
        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        // Assert
        List<GrantedAuthority> expectedAuthorities = List.of(
            new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_USER")
        );
        assertEquals(expectedAuthorities, authorities);
    }

    @Test
    void testGetAuthoritiesForUser() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setRole(UserRoles.USER);

        // Act
        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        // Assert
        List<GrantedAuthority> expectedAuthorities = List.of(
            new SimpleGrantedAuthority("ROLE_USER")
        );
        assertEquals(expectedAuthorities, authorities);
    }

    @Test
    void testGetUsername() {
        // Arrange
        String cpf = "123.456.789-00";
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);

        // Act
        String username = usuario.getUsername();

        // Assert
        assertEquals(cpf, username);
    }

    @Test
    void testGetPassword() {
        // Arrange
        String senha = "senhaCriptografada";
        Usuario usuario = new Usuario();
        usuario.setSenha(senha);

        // Act
        String password = usuario.getPassword();

        // Assert
        assertEquals(senha, password);
    }
}

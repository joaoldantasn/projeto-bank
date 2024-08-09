package com.br.accenture.eBank.ebank.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.accenture.eBank.ebank.entities.enums.auth.UserRoles;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String cpf;
    private String nomeUsuario;
    private String telefone;
    private String senha;
    private UserRoles role;

    @ManyToOne
    @JoinColumn(name = "agencia_id")
    @JsonBackReference
    private Agencia agencia;;
	
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Conta> contas = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long idUsuario, String cpf, String senha, String nomeUsuario, String telefone, UserRoles role, Agencia agencia, Endereco endereco, Set<Conta> contas) {
        this.idUsuario = idUsuario;
        this.cpf = cpf;
        this.senha = senha;
        this.nomeUsuario = nomeUsuario;
        this.telefone = telefone;
        this.role = role;
        this.agencia = agencia;
        this.endereco = endereco;
        this.contas = contas;
    }
    
    //public Usuario(Long idUsuario,String cpf, String nomeUsuario, String telefone) {
    	//this.idUsuario = idUsuario;
    	//this.cpf = cpf;
    	//this.nomeUsuario = nomeUsuario;
    	//this.telefone = telefone;
    //}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRoles.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
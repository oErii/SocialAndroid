package com.example.progettosocial.api.dto.response;

import java.io.Serializable;

public class UtenteInfoDTO implements Serializable {
    private String username,email,nome,cognome;
    private Long id;

    public UtenteInfoDTO(String username, String email, String nome, String cognome, Long id) {
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.id = id;
    }

    public UtenteInfoDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

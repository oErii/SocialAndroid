package com.example.progettosocial.model;

import java.io.Serializable;

public class Utente implements Serializable {
    private String username;
    private String email;
    private String password;
    private String nome, cognome;

    public Utente(String username, String email, String password, String nome, String cognome) {
        this.username = username;
        this.email=email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
}
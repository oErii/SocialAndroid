package com.example.progettosocial.api.dto.request;

public class UpdateUtenteRequest {
    private String usernameAggiornato;
    private String nomeAggiornato;
    private String cognomeAggiornato;

    public UpdateUtenteRequest(String usernameAggiornato, String nomeAggiornato, String cognomeAggiornato) {
        this.usernameAggiornato = usernameAggiornato;
        this.nomeAggiornato = nomeAggiornato;
        this.cognomeAggiornato = cognomeAggiornato;
    }

    public UpdateUtenteRequest() {
    }

    public String getUsernameAggiornato() {
        return usernameAggiornato;
    }

    public void setUsernameAggiornato(String usernameAggiornato) {
        this.usernameAggiornato = usernameAggiornato;
    }

    public String getNomeAggiornato() {
        return nomeAggiornato;
    }

    public void setNomeAggiornato(String nomeAggiornato) {
        this.nomeAggiornato = nomeAggiornato;
    }

    public String getCognomeAggiornato() {
        return cognomeAggiornato;
    }

    public void setCognomeAggiornato(String cognomeAggiornato) {
        this.cognomeAggiornato = cognomeAggiornato;
    }
}

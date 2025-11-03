package com.example.progettosocial.api.dto.request;

public class DeleteUtenteRequest {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DeleteUtenteRequest(){}

    public DeleteUtenteRequest(String password) {
        this.password = password;
    }
}

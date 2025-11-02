package com.example.progettosocial.api.dto.request;

public class UpdateUsernameRequest {
    private String nuovoUsername;

    public UpdateUsernameRequest(String nuovoUsername) {
        this.nuovoUsername = nuovoUsername;
    }

    public UpdateUsernameRequest() {
    }

    public String getNuovoUsername() {
        return nuovoUsername;
    }

    public void setNuovoUsername(String nuovoUsername) {
        this.nuovoUsername = nuovoUsername;
    }
}

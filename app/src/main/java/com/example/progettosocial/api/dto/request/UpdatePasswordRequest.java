package com.example.progettosocial.api.dto.request;

public class UpdatePasswordRequest {
    private String nuovaPassword;

    public UpdatePasswordRequest(String nuovaPassword) {
        this.nuovaPassword = nuovaPassword;
    }

    public UpdatePasswordRequest() {
    }

    public String getNuovaPassword() {
        return nuovaPassword;
    }

    public void setNuovaPassword(String nuovaPassword) {
        this.nuovaPassword = nuovaPassword;
    }
}

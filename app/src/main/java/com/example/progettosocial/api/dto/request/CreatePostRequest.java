package com.example.progettosocial.api.dto.request;

public class CreatePostRequest {
    private String testo;

    public CreatePostRequest(String testo) {
        this.testo = testo;
    }

    public CreatePostRequest(){}

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}

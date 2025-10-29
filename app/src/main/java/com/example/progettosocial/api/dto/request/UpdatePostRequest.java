package com.example.progettosocial.api.dto.request;

public class UpdatePostRequest {
    private Long idPost;
    private String nuovoTesto;

    public UpdatePostRequest(Long idPost, String nuovoTesto) {
        this.idPost = idPost;
        this.nuovoTesto = nuovoTesto;
    }

    public UpdatePostRequest() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getNuovoTesto() {
        return nuovoTesto;
    }

    public void setNuovoTesto(String nuovoTesto) {
        this.nuovoTesto = nuovoTesto;
    }
}

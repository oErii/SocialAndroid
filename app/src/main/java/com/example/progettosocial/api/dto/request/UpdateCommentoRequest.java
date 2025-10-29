package com.example.progettosocial.api.dto.request;

public class UpdateCommentoRequest {
    private Long idCommento;
    private String nuovoTesto;

    public UpdateCommentoRequest(Long idCommento, String nuovoTesto) {
        this.idCommento = idCommento;
        this.nuovoTesto = nuovoTesto;
    }

    public UpdateCommentoRequest() {
    }

    public Long getIdCommento() {
        return idCommento;
    }

    public void setIdCommento(Long idCommento) {
        this.idCommento = idCommento;
    }

    public String getNuovoTesto() {
        return nuovoTesto;
    }

    public void setNuovoTesto(String nuovoTesto) {
        this.nuovoTesto = nuovoTesto;
    }
}

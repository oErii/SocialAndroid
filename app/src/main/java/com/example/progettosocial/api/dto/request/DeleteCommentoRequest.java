package com.example.progettosocial.api.dto.request;

public class DeleteCommentoRequest {
    private Long idCommento;

    public DeleteCommentoRequest(){}

    public DeleteCommentoRequest(Long idCommento) {
        this.idCommento = idCommento;
    }

    public Long getIdCommento() {
        return idCommento;
    }

    public void setIdCommento(Long idCommento) {
        this.idCommento = idCommento;
    }
}

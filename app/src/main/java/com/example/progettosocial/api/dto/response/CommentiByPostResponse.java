package com.example.progettosocial.api.dto.response;

import java.io.Serializable;
import java.util.List;

public class CommentiByPostResponse implements Serializable {
    private List<CommentoDTO> listaCommenti;

    public CommentiByPostResponse(List<CommentoDTO> listaCommenti) {
        this.listaCommenti = listaCommenti;
    }

    public CommentiByPostResponse(){}

    public List<CommentoDTO> getListaCommenti() {
        return listaCommenti;
    }

    public void setListaCommenti(List<CommentoDTO> listaCommenti) {
        this.listaCommenti = listaCommenti;
    }
}

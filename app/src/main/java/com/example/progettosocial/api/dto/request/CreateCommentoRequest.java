package com.example.progettosocial.api.dto.request;

public class CreateCommentoRequest {
    private Long idPost;
    private String testo;

    public CreateCommentoRequest(){}

    public CreateCommentoRequest(Long idPost, String testo){
        this.idPost=idPost;
        this.testo=testo;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }


}

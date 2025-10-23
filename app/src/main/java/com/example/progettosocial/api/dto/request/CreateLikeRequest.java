package com.example.progettosocial.api.dto.request;

public class CreateLikeRequest {
    private Long idPost;

    public CreateLikeRequest(Long idPost) {
        this.idPost = idPost;
    }

    public CreateLikeRequest(){}

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }
}

package com.example.progettosocial.api.dto.request;

public class DeletePostRequest {
    private Long idPost;

    public DeletePostRequest(Long idPost) {
        this.idPost = idPost;
    }

    public DeletePostRequest() {
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }
}

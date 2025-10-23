package com.example.progettosocial.api.dto.response;

public class UtenteDTO {
    private Long id;
    private String username;

    public UtenteDTO(){}

    public UtenteDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

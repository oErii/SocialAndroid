package com.example.progettosocial.api.dto.response;

public class PostDTO {
    private Long id;
    private String testo;
    private String dataPubblicazione;
    private UtenteDTO autore;
    private Long numeroCommenti;
    private Long numeroLikes;
    private boolean liked;
    private boolean mine;

    public PostDTO(Long id, String testo, String dataPubblicazione, UtenteDTO autore, Long numeroCommenti, Long numeroLikes, boolean liked, boolean mine) {
        this.id = id;
        this.testo = testo;
        this.dataPubblicazione = dataPubblicazione;
        this.autore = autore;
        this.numeroCommenti = numeroCommenti;
        this.numeroLikes = numeroLikes;
        this.liked = liked;
        this.mine = mine;
    }

    public PostDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(String dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public UtenteDTO getAutore() {
        return autore;
    }

    public void setAutore(UtenteDTO autore) {
        this.autore = autore;
    }

    public Long getNumeroCommenti() {
        return numeroCommenti;
    }

    public void setNumeroCommenti(Long numeroCommenti) {
        this.numeroCommenti = numeroCommenti;
    }

    public Long getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(Long numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }
}

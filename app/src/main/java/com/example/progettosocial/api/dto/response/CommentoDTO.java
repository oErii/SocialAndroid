package com.example.progettosocial.api.dto.response;

public class CommentoDTO {
    private Long id;
    private UtenteDTO autore;
    private String testo;
    private String dataPubblicazione;

    public CommentoDTO(Long id, UtenteDTO autore, String testo, String dataPubblicazione) {
        this.id = id;
        this.autore = autore;
        this.testo = testo;
        this.dataPubblicazione = dataPubblicazione;
    }

    public CommentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtenteDTO getAutore() {
        return autore;
    }

    public void setAutore(UtenteDTO autore) {
        this.autore = autore;
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
}

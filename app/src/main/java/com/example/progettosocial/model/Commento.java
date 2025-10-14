package com.example.progettosocial.model;

public class Commento {
    private String nomeCompleto;
    private String content;
    private String data;
    private int likes;

    public Commento(String nomeCompleto, String content, String data) {
        this.nomeCompleto = nomeCompleto;
        this.content = content;
        this.data = data;
        this.likes = 0;
    }

    public Commento(){}

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}

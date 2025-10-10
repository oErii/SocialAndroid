package com.example.progettosocial.model;

import java.time.LocalDateTime;

public class Post {
    private Utente user;
    private String content;
    private LocalDateTime data;
    private int likes;

    public Post(Utente user, String content, LocalDateTime date) {
        this.user = user;
        this.content = content;
        this.data = date;
        this.likes = 0;
    }

    // getter e setter

    public Utente getUser() {
        return user;
    }
    public String getContent() { return content; }
    public LocalDateTime getDate() { return data; }
    public int getLikes() { return likes; }
    public void setContent(String content) { this.content = content; }
    public void addLike() { likes++; }
}
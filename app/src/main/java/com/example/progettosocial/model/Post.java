package com.example.progettosocial.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Post implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String testo;
    @ColumnInfo
    private String data;
    @ColumnInfo
    private Long likes;
    @ColumnInfo
    private Long numeroCommenti;
    @ColumnInfo
    private boolean liked;
    @ColumnInfo
    private boolean mine;

    public Post(Long id, String username, String testo, String data, Long likes, Long numeroCommenti, boolean liked, boolean mine) {
        this.id = id;
        this.username = username;
        this.testo = testo;
        this.data = data;
        this.likes = likes;
        this.numeroCommenti = numeroCommenti;
        this.liked = liked;
        this.mine = mine;
    }

    public Post(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getNumeroCommenti() {
        return numeroCommenti;
    }

    public void setNumeroCommenti(Long numeroCommenti) {
        this.numeroCommenti = numeroCommenti;
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
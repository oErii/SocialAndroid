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
    private String nomeCompleto;
    @ColumnInfo
    private String content;
    @ColumnInfo
    private String data;
    @ColumnInfo
    private int likes;

    public Post(){}

    public Post(Long id,String nomeCompleto, String content, String date) {
        this.nomeCompleto = nomeCompleto;
        this.content = content;
        this.data = date;
        this.likes = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
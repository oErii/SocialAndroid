package com.example.progettosocial.model;

import java.util.ArrayList;
import java.util.List;

public class DatabasePost {

    private static DatabasePost instance;

    private List<Post> posts;

    private DatabasePost(){
        posts = new ArrayList<>();
    }

    public static DatabasePost getInstance() {
        if(instance == null){
            instance = new DatabasePost();
        }
        return instance;
    }

    public List<Post> getPosts() {
        return posts;
    }
}

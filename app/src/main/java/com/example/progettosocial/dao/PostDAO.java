package com.example.progettosocial.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.progettosocial.model.Post;

import java.util.List;

@Dao
public interface PostDAO {
    @Insert
    void insertPost(Post post);
    @Update
    void updatePost(Post post);
    @Delete
    void deletePost(Post post);
    @Query("DELETE FROM Post")
    void deleteAll();

    @Query("SELECT * FROM Post")
    List<Post> getAll();

    @Query("SELECT * FROM Post WHERE id =:idNum ")
    Post selctByID(int idNum);
}

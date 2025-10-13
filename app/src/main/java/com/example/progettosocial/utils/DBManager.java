package com.example.progettosocial.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.model.Post;

@Database(entities = {Post.class},version = 1)
public abstract class DBManager extends RoomDatabase {
    private static DBManager instance;
    public static DBManager getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context, DBManager.class,"SocialDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract PostDAO getPostDao();
}

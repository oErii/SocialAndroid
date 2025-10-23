package com.example.progettosocial.api;

import android.content.Context;

import com.example.progettosocial.api.dto.request.CreateLikeRequest;
import com.example.progettosocial.api.dto.request.CreatePostRequest;
import com.example.progettosocial.api.dto.request.LoginRequest;
import com.example.progettosocial.api.dto.request.RegistrazioneRequest;
import com.example.progettosocial.utils.Preferences;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ApiManager {

    private static ApiManager instance;
    private final ObjectMapper mapper = new ObjectMapper();
    private final OkHttpClient client = new OkHttpClient();
    private final String BASE_URL="https://socialmaster.ddns.net";
    private final String API_KEY="MasterSviluppoFeb25";
    private ApiManager(){}

    public static ApiManager getInstance() {
        if (instance==null){
            instance  = new ApiManager();
        }
        return instance;
    }

    public void registra(RegistrazioneRequest registrazioneRequest, Callback callback){
        String body =null;
        try{
            body= mapper.writeValueAsString(registrazioneRequest);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(BASE_URL+"/Utente/createUtente")
                .post(requestBody)
                .header("key",API_KEY)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void login(LoginRequest loginRequest, Callback callback){
        String body=null;
        try {
            body = mapper.writeValueAsString(loginRequest);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(BASE_URL+"/Utente/login")
                .post(requestBody)
                .header("key",API_KEY)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void logout(Callback callback, Context context){
        Request request = new Request.Builder()
                .url(BASE_URL+"/Utente/logout")
                .get()
                .header("key",API_KEY)
                .header("Authorization",Preferences.loadTKN(context))
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void getLastPost(Callback callback, Context context){
        Request request =new Request.Builder()
                .url(BASE_URL+"/Post/getLastPost")
                .get()
                .header("key",API_KEY)
                .header("Authorization", Preferences.loadTKN(context))
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void creaPost(CreatePostRequest createPostRequest, Callback callback, Context context){
        String body=null;
        try {
            body = mapper.writeValueAsString(createPostRequest);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json"));
        Request request =new Request.Builder()
                .url(BASE_URL+"/Post/createPost")
                .post(requestBody)
                .header("key",API_KEY)
                .header("Authorization", Preferences.loadTKN(context))
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void toggleLike(CreateLikeRequest createLikeRequest, Callback callback, Context context){
        String body=null;
        try{
            body=mapper.writeValueAsString(createLikeRequest);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json"));
        Request request =new Request.Builder()
                .url(BASE_URL+"/Like/toggleLike")
                .post(requestBody)
                .header("key",API_KEY)
                .header("Authorization", Preferences.loadTKN(context))
                .build();
        client.newCall(request).enqueue(callback);
    }

}

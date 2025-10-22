package com.example.progettosocial.api;

import com.example.progettosocial.api.dto.request.LoginRequest;
import com.example.progettosocial.api.dto.request.RegistrazioneRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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

    public void logout(Callback callback){
        Request request = new Request.Builder()
                .url(BASE_URL+"/Utente/logout")
                .get()
                .header("key",API_KEY)
                .header("Authorization","Il vostro JWT")
                .build();
        client.newCall(request).enqueue(callback);
    }

}
